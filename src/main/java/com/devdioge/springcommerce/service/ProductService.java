package com.devdioge.springcommerce.service;

import com.devdioge.springcommerce.dto.ProductDTO;
import com.devdioge.springcommerce.entity.Product;
import com.devdioge.springcommerce.repository.ProductRepository;
import com.devdioge.springcommerce.service.exception.DatabaseException;
import com.devdioge.springcommerce.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto Nao Encontrado"));
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

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product product = productRepository.getReferenceById(id);
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setImgUrl(dto.getImgUrl());
            productRepository.save(product);
            return modelMapper.map(product, ProductDTO.class);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Produto Nao Encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
       if(!productRepository.existsById(id)){
           throw new ResourceNotFoundException("Produto Nao Encontrado");
       }
       try {
           productRepository.deleteById(id);
       }
       catch (DataIntegrityViolationException e) {
           throw new DatabaseException("Falha de integridade referencial");
       }
    }
}
