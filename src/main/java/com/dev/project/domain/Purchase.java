package com.dev.project.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@lombok.Data
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantity;
    private Double unitaryValue;
    private Double totalValue;
    private Double icms;
    private Double ipi;
    private Double shipping;
    private Double netValue;
    private Double totalAmountInvoice;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Data data;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cfop cfop;


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

