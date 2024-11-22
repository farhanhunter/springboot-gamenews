package com.example.mysandbox.dto.request;

import com.example.mysandbox.enums.CategoryType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotNull(message = "Category type is mandatory")
    private CategoryType name;
}
