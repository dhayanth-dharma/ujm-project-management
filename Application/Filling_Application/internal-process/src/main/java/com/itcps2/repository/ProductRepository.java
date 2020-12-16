package com.itcps2.repository;

import org.springframework.data.repository.CrudRepository;

import com.itcps2.filling.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
//	List<User> findAll();
//	User findByName(String name);
}