package com.eduardomallmann.study.reactive.spring.cloud.gatewayservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Value("${route.car}")
	private String carAddress;
    @Value("${route.fave}")
    private String faveCarAddress;

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
					   .route("car-service", r -> r.path("/cars").uri(carAddress))
                       .route("fave-cars-service", r -> r.path("/fave-cars").uri(faveCarAddress))
					   .build();
	}

}
