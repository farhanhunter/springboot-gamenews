package com.example.mysandbox.entity;

import com.example.mysandbox.enums.CategoryType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType name;

    @Column(unique = true, nullable = false, length = 50)
    private String slug;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

//    @ManyToMany(mappedBy = "categories")
//    private Set<Article> articles = new HashSet<>();

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
