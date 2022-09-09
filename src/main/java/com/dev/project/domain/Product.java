package com.dev.project.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@lombok.Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String code;
    private String unity;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Ncm ncm;


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

