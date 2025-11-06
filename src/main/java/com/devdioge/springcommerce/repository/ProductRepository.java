package com.devdioge.springcommerce.repository;

import com.devdioge.springcommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {


}
