package com.eduardomallmann.study.okta.reactive.spring.cloud.favecarsservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {

    private String name;
    private LocalDate releaseDate;

}
