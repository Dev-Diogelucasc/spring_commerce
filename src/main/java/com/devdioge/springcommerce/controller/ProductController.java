package com.devdioge.springcommerce.controller;

import com.devdioge.springcommerce.dto.ProductDTO;
import com.devdioge.springcommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Parâmetro na rota
    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findBytId(id);
    }

    // Pageable, para me retornar paginado, ao inves de retornar toda a lista
    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }
}
