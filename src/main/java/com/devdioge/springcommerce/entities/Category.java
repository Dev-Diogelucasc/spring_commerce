package com.devdioge.springcommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // relacionamento muitos-para-muitos
    @ManyToMany(mappedBy = "categories")
    @Getter
    private Set<Product> products = new HashSet<>();
}
