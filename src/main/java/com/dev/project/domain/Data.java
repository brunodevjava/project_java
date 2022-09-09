package com.dev.project.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@lombok.Data
@NoArgsConstructor
@Entity
@Table(name = "data")
public class Data {


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

