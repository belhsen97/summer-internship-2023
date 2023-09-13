package tn.esprit.Config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder,  FilterAuthentificate filterAuthentificate) {
        return builder.routes()
                .route("user-service", r -> r.path("/management-offers/user-service/**").uri("lb://user-service"))
                .route("offer-pro-service", r -> r.path("/management-offers/offer-pro-service/**").uri("lb://offer-pro-service"))
                .route("mail-service", r -> r.path("/management-offers/mail-service/**")
                       // .filters(f -> f.filter(filterAuthentificate.apply( new FilterAuthentificate.Config())))
                        .uri("lb://mail-service"))


                .route("discovery-server", r -> r.path("/eureka/web").filters(f -> f.setPath("/")).uri("http://localhost:8761"))
                .route("discovery-server-static", r -> r.path("/eureka/**") .uri("http://localhost:8761"))
                .build();
    }

}


