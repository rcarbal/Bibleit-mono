package com.bibleit.bibleitmono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BibleitMonoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibleitMonoApplication.class, args);
	}

}
