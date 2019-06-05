/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redpic.redpicrest;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author alayon
 */
@ApplicationPath("webapi")
public class MyApp extends Application {

    private final String PATH = "C:\\Users\\alayon\\Desktop\\SD\\RedPicRest\\";
    private final String GOOGLE_CREDENTIALS_NAME_FILE = "serviceAccountKey.json";
  
    private FirebaseApp app;
    
    public static FirebaseAuth auth;
    public static FirebaseDatabase database;

    public MyApp() {
        try {
            loadConfigurationFireBase();
        } catch (IOException ex) {
            Logger.getLogger(MyApp.class.getName()).log(Level.SEVERE, null, ex);
            exit(-1);
        }
    }

    private void loadConfigurationFireBase() throws FileNotFoundException, IOException {
        FileInputStream serviceAccount
                = new FileInputStream(PATH + GOOGLE_CREDENTIALS_NAME_FILE);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://redpic-d8d79.firebaseio.com")
                .build();

        app = FirebaseApp.initializeApp(options);
        auth = FirebaseAuth.getInstance(app);
        database = FirebaseDatabase.getInstance(app);            
    }
}
