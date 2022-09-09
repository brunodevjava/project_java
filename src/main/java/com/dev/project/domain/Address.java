package com.dev.project.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@lombok.Data
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String complement;
    private String district;
    private String cep;
    private String phoneOne;
    private String phoneTwo;
    private String cellOne;
    private String cellTwo;
    private String email;

    private Long cityId;
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

