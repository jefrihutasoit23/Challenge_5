package com.aplikasi.chapter4;

import com.aplikasi.chapter4.binarfud.controller.fileupload.FileStorageProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@OpenAPIDefinition
public class Chapter4Application {
	public static void main(String[] args) {
		SpringApplication.run(Chapter4Application.class, args);
	}
}