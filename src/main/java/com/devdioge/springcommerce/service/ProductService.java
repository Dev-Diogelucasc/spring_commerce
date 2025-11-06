package com.devdioge.springcommerce.service;

import com.devdioge.springcommerce.dto.ProductDTO;
import com.devdioge.springcommerce.entity.Product;
import com.devdioge.springcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findBytId(Long id){
        Product product = productRepository.findById(id).get();
        return new ProductDTO(product);
    }
}
