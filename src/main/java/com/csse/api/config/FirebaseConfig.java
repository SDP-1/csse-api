package com.csse.api.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    // No @Bean annotation, just an init method
    public void initializeFirebase() {
        try {
            // Check if the default FirebaseApp already exists
            if (FirebaseApp.getApps().isEmpty()) {
                FileInputStream serviceAccount =
                        new FileInputStream("src/main/resources/serviceAccountKey.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://vkkk-66b3b-default-rtdb.firebaseio.com")
                        .build();

                FirebaseApp.initializeApp(options);
                logger.info("Firebase initialized successfully.");
            } else {
                logger.info("Firebase has already been initialized.");
            }
        } catch (IOException e) {
            logger.error("Error initializing Firebase: ", e);
        }
    }
}
