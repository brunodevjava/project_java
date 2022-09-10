package com.dev.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CfopTO {
    private Long id;
    private String code;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
