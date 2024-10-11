package com.csse.api;

import com.csse.api.config.FirebaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

	private final FirebaseConfig firebaseConfig;

	public Application(FirebaseConfig firebaseConfig) {
		this.firebaseConfig = firebaseConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void init() {
		firebaseConfig.initializeFirebase();
	}
}
