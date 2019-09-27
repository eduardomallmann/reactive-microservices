package com.eduardomallmann.study.reactive.spring.cloud.carservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Car {
    @Id
    private UUID id;
    private String name;
    private LocalDate releaseDate;
}
