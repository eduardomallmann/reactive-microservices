package com.eduardomallmann.study.reactive.spring.cloud.favecarsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureWireMock
class FaveCarsControllerIT {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void faveCars() {
        Car carResult = Car.builder()
                                .name("ID. BUZZ")
                                .releaseDate(LocalDate.of(2021,12,1))
                                .build();
        stubFor(get(urlMatching("/cars"))
                        .willReturn(aResponse()
                                            .withStatus(HttpStatus.OK.value())
                                            .withHeader("Content-Type", "application/json")
                                            .withBodyFile("cars.json"))
        );
        webTestClient.get().uri("/fave-cars")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Car.class)
                .isEqualTo(List.of(carResult));
    }
}