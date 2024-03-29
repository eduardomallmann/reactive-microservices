package com.eduardomallmann.study.reactive.spring.cloud.carservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.UUID;

@Slf4j
@SpringBootApplication
public class CarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CarRepository repository) {
        // Electric VWs from https://www.vw.com/electric-concepts/
        // Release dates from https://www.motor1.com/features/346407/volkswagen-id-price-on-sale/
        Car ID = new Car(UUID.randomUUID(), "ID.", LocalDate.of(2019, Month.DECEMBER, 1));
        Car ID_CROZZ = new Car(UUID.randomUUID(), "ID. CROZZ", LocalDate.of(2021, Month.MAY, 1));
        Car ID_VIZZION = new Car(UUID.randomUUID(), "ID. VIZZION", LocalDate.of(2021, Month.DECEMBER, 1));
        Car ID_BUZZ = new Car(UUID.randomUUID(), "ID. BUZZ", LocalDate.of(2021, Month.DECEMBER, 1));
        Set<Car> vwConcepts = Set.of(ID, ID_BUZZ, ID_CROZZ, ID_VIZZION);

        return args -> repository.deleteAll()
                               .thenMany(Flux.just(vwConcepts).flatMap(repository::saveAll))
                               .thenMany(repository.findAll())
                               .subscribe(car -> log.info("saving " + car.toString()));
    }
}
