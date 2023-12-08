package com.github.aleffalves.apiblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank @Email
    private String email;

    @NotBlank @Size(min = 8)
    private String senha;
}
