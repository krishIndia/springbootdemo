package com.mfg.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class DemoApplication  extends SpringBootServletInitializer  {
	
	//Below method needed to make war file deployable, not needed for jar
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(DemoApplication.class);
	    } 
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("Running........######################################################....");
    }
}
