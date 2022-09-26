package com.dev.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTO {
    private Long id;
    private String token;
    private String name;
    private String email;
    private Boolean status;
    private Boolean googleAuth;
}