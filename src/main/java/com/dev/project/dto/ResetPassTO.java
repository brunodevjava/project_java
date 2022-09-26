package com.dev.project.dto;

import lombok.Data;

@Data
public class ResetPassTO {
    private String token;
    private String newPass;
}
