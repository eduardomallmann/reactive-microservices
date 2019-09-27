package com.eduardomallmann.study.reactive.spring.cloud.favecarsservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/fave-cars")
@RequiredArgsConstructor
public class FaveCarsController {

    @Value("${routes.uri.cars}")
    private String carUri;
    private final WebClient.Builder carClient;

    @GetMapping
    public Flux<Car> faveCars() {
        return carClient.build().get().uri(carUri)
                       .retrieve().bodyToFlux(Car.class)
                       .filter(this::isFavorite);
    }

    private boolean isFavorite(Car car) {
        return car.getName().equals("ID. BUZZ");
    }

}
