package com.dev.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DataTO {
    private Long id;
    private String kindOfPerson;
    private String name;
    private String fantasyName;
    private String cnpj;
    private String cpf;
    private String insest;
    private Long addressId;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
