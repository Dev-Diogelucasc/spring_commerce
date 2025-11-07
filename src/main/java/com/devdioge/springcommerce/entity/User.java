package com.devdioge.springcommerce.entity;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;

    @Column(unique = true) // campo e-mail, não tera email igual
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String password;

    // relacionamento um-para-muitos
    @EqualsAndHashCode.Exclude // performace melhor
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();
}
