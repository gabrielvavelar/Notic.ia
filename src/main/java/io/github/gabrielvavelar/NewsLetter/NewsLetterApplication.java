package io.github.gabrielvavelar.NewsLetter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class NewsLetterApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsLetterApplication.class, args);
	}

}
