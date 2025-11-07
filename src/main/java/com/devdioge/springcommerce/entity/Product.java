package com.devdioge.springcommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_product")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;

    @Column(columnDefinition = "TEXT") // hora de mapear, ela vai ser um texto longo,  ao inves do varChar
    private String description;
    private Double price;
    private String imgUrl;

    // relacionamento muitos-para-muitos
    @ManyToMany
    @JoinTable(name = "tb_product_category",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    public List<Order> getOrders() {
        return items.stream().map(OrderItem::getOrder).toList();
    }
}
