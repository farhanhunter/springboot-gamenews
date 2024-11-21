package com.example.mysandbox.entity;

import com.example.mysandbox.enums.TagType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TagType name;

    @Column(unique = true, nullable = false, length = 50)
    private String slug;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (this.slug == null && this.name != null) {
            this.slug = this.name.name().toLowerCase().replace("_", "-");
        }
    }

    public String getDisplayName() {
        return name != null ? name.getDisplayName() : null;
    }
}
