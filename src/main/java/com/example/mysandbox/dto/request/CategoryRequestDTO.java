package com.example.mysandbox.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;
}
