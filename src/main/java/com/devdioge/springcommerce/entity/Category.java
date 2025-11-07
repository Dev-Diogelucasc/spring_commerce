package com.devdioge.springcommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_category")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;

    // relacionamento muitos-para-muitos
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();
}
