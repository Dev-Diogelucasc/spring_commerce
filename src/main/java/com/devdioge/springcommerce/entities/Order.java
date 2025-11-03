package com.devdioge.springcommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "tb_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // dado que armazena a data e a hora, mas sem informações de fuso horário
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    private OrderSatus status;

    // relacionamento muitos-para-um
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
}
