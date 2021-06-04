package com.junaid18183.sampleapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import com.junaid18183.sampleapplication.configuration.JpaConfiguration;

@Configuration
@ComponentScan
@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.junaid18183.sampleapplication"})
	public class SpringBootCRUDApp extends SpringBootServletInitializer implements WebApplicationInitializer {

	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(SpringBootCRUDApp.class);
	    }

	    public static void main(String[] args) throws Exception {
	        SpringApplication.run(SpringBootCRUDApp.class, args);
	    }

	}

