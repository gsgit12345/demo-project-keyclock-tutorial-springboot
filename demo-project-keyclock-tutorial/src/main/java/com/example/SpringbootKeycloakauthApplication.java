package com.example;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
@SecurityScheme(
        name = "Keycloak"
        , openIdConnectUrl = "http://127.0.0.1:8080/realms/dive-dev-org/.well-known/openid-configuration"
        , scheme = "bearer"
        , type = SecuritySchemeType.OPENIDCONNECT
        , in = SecuritySchemeIn.HEADER
)
public class SpringbootKeycloakauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootKeycloakauthApplication.class);
        System.out.println("Hello World!");
    }
}

//   http://localhost:8080/restaurant/public/list