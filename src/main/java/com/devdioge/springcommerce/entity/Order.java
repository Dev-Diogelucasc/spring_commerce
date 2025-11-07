package com.devdioge.springcommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_order")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // dado que armazena a data e a hora, mas sem informações de fuso horário
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    private OrderSatus status;

    // relacionamento muitos-para-um
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    // relacionamento um-para-um
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public List<Product> getProducts() {
        return items.stream().map(OrderItem::getProduct).toList();
    }


}
