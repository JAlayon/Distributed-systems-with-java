package com.redpic.services;


import com.google.gson.Gson;
import com.redpic.utils.User;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.ClientErrorException;

/**
 * @author alayon
 */
public class ApiRest {

    private Client client;
    private WebTarget webTarget;
   
    private static String SERVER;
    private static String BASE_URI;
    
    private static ApiRest instance;
    
    private ApiRest() {
        BASE_URI = "http://" + SERVER + ":8080/RedPicRest/webapi";
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("users");
     }
    
    public static ApiRest getInstance(){
        if(instance == null)
            instance = new ApiRest();
        return instance;
    }
    
    public static void setServer(String server){
        SERVER = server;
    }

    public Response addUser(User user) throws ClientErrorException {
        String userJson = new Gson().toJson(user);
        Response response = webTarget.request().post(Entity.json(userJson));
        return response;
    }

    public Response login(User user) throws ClientErrorException {
        String UserJson = new Gson().toJson(user);
        Response response = webTarget.path("login").request().post(Entity.json(UserJson));
        return response;
    }

    public void close() {
        client.close();
    }

}
