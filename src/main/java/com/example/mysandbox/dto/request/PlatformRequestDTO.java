package com.example.mysandbox.dto.request;

import com.example.mysandbox.enums.PlatformType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlatformRequestDTO {
    @NotNull(message = "Platform type is mandatory")
    private PlatformType name;
}
