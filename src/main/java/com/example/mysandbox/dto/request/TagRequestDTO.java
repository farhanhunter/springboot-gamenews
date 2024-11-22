package com.example.mysandbox.dto.request;

import com.example.mysandbox.enums.TagType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TagRequestDTO {
    @NotNull(message = "TagType is mandatory")
    private TagType name;
}
