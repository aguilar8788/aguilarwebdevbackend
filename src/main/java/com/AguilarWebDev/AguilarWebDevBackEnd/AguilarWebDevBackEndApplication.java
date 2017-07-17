package com.AguilarWebDev.AguilarWebDevBackEnd;

import com.AguilarWebDev.AguilarWebDevBackEnd.storage.StorageProperties;
import com.AguilarWebDev.AguilarWebDevBackEnd.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class AguilarWebDevBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(AguilarWebDevBackEndApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
