package com.dev.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductTO {
    private Long id;
    private String name;
    private String category;
    private String code;
    private String unity;
    private Long ncmId;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
