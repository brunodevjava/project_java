package com.dev.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CityTO {
    private Long id;
    private String name;
    private String state;
    private String country;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
