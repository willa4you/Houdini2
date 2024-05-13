package it.univr.houdini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import it.univr.houdini.FileUpload.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class HoudiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoudiniApplication.class, args);
	}

}