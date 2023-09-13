package tn.esprit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
////@Component est un stéréotype générique pour tout composant géré par Spring.
//// @Repository, @Service et @Controller sont des spécialisations de
//// @Component pour des cas d'utilisation plus spécifiques
//// (dans les couches de persistance, de service et de présentation, respectivement)
//@EnableEurekaClient
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}