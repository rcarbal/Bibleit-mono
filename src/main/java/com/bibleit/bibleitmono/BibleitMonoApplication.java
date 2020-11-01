package com.bibleit.bibleitmono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BibleitMonoApplication {

	private final static Logger log = LoggerFactory.getLogger(BibleitMonoApplication.class);

	public static void main(String[] args) {
		log.info("Hello info");
		log.debug("Hello debug");
		SpringApplication.run(BibleitMonoApplication.class, args);
	}

}
