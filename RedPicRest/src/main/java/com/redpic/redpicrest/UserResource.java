package com.redpic.redpicrest;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.redpic.models.HttpCode;
import com.redpic.models.User;
import java.util.logging.Level;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final String TABLE_NAME = "users";
    private DatabaseReference tblUsers;

    private UserResource() {
        tblUsers = MyApp.database.getReference(TABLE_NAME);
    }

    @POST
    @Path("/login")
    public Response login(final User user) throws InterruptedException {
        logger.debug("Trying to login the user {}", user);

        HttpCode httpCode = HttpCode.OK;
        final User userToReturn = new User();

        try {
            String email = user.getEmail();
            UserRecord user_record = MyApp.auth.getUserByEmail(email);
            final String uid = user_record.getUid();
            tblUsers.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot ds) {
                    synchronized (userToReturn) {
                        User userFromDB = ds.getValue(User.class);
                        if (userFromDB.getPassword().equals(user.getPassword())) {
                            userToReturn.setEmail(userFromDB.getEmail());
                            userToReturn.setPassword(userFromDB.getPassword());
                            userToReturn.setName(userFromDB.getName());
                        }
                        userToReturn.notify();
                    }
                }

                @Override
                public void onCancelled(DatabaseError de) {
                }
            });

        } catch (FirebaseAuthException ex) {
            logger.error("The email especified has not found", ex);
            httpCode = HttpCode.NOT_FOUND;
            return Response.status(httpCode.getCode()).entity(null).build();
        }

        synchronized (userToReturn) {
            userToReturn.wait();
            if (!user.getPassword().equals(userToReturn.getPassword())) {
                httpCode = HttpCode.VALIDATION_ERROR;
            }
            return Response.status(httpCode.getCode()).entity(userToReturn).build();
        }
    }

    @POST
    public Response addUser(User user) {
        logger.debug("Registering a new user with body {}", user);
        HttpCode httpCode = HttpCode.CREATED;
     
        CreateRequest request = new CreateRequest()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setDisplayName(user.getName());

        try {
            UserRecord user_record = MyApp.auth.createUser(request);
            tblUsers.child(user_record.getUid())
                    .setValueAsync(user);

        } catch (FirebaseAuthException ex) {
            logger.error("Has ocurred an error ", ex);
            httpCode = HttpCode.VALIDATION_ERROR;
            return Response.status(httpCode.getCode()).entity(null).build();
        }

        return Response.status(httpCode.getCode()).entity(user).build();
    }

}
