package com.dev.project.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@lombok.Data
@NoArgsConstructor
@Entity
@Table(name = "city")
public class City {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String state;
    private String country;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;


    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    }

    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    }

}

