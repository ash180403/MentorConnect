package com.cascade.mentorconnect.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig() {
    	return new OpenAPI()
                .info(new Info()
                        .title("Mentors")
                        .version("1.0.0")  
                        .description("API documentation for MentorConnect platform")
                        .contact(new Contact()
//                                .name("Sparkids Team")
                                .email("support@mentorsconnect.com")
                                .url("https://www.mentors.com.com")) 
                );
    }
}

