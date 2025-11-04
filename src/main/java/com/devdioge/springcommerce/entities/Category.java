package com.devdioge.springcommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_category")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;

    // relacionamento muitos-para-muitos
    @ManyToMany(mappedBy = "categories")
    @Getter
    private Set<Product> products = new HashSet<>();
}
