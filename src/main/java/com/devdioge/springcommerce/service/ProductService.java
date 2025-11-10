package com.devdioge.springcommerce.service;

import com.devdioge.springcommerce.dto.ProductDTO;
import com.devdioge.springcommerce.entity.Product;
import com.devdioge.springcommerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Copiar dados  da entity para o DTO
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public ProductDTO findBytId(Long id) {
        Product product = productRepository.findById(id).get();
        return modelMapper.map(product, ProductDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(product -> modelMapper.map(product, ProductDTO.class));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = modelMapper.map(dto, Product.class);
        productRepository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }
}
