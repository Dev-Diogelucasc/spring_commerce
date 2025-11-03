package com.devdioge.springcommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "tb_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name, email, phone;
    private LocalDate birthDate;
    private String password;

    // relacionamento um-para-muitos
    @EqualsAndHashCode.Exclude // remover e melhorar performace
    @OneToMany(mappedBy = "client")
    @Getter
    private List<Order> orders = new ArrayList<>();
}
