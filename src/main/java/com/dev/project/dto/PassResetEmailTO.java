package com.dev.project.dto;

import lombok.Data;

@Data
public class PassResetEmailTO {
    private String email;
    private String url;
}
