package com.devdioge.springcommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    // validação para nome, não vir vazio
    @NotBlank(message = "Campo Requerido")
    @Size(min = 3, max = 80, message = "O nome deve ter entre 3 e 80 caracteres")
    private String name;
    @NotBlank(message = "Campo Requerido")
    @Size(min = 10, message = "A descrição deve ter no minimo 10 caracteres")
    private String description;
    @Positive(message = "O preço deve ser um valor positivo")
    private Double price;
    private String imgUrl;
}
