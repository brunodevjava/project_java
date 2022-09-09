package com.dev.project.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@lombok.Data
@NoArgsConstructor
@Entity
@Table(name = "data")
public class Cfop {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kindOfPerson;
    private String name;
    private String fantasyName;
    private String cnpj;
    private String cpf;
    private String insest;
   // private Long addressId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;



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

