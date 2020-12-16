package com.potting.repo;
import org.springframework.data.repository.CrudRepository;

import com.pottin.model.Product;


public interface ProductRepository extends CrudRepository<Product, Integer> {

}