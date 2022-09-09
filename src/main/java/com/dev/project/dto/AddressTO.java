package com.dev.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressTO {
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
}
