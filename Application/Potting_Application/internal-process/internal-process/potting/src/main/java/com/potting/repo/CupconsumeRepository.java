package com.potting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.pottin.model.Cupconsume;


public interface CupconsumeRepository extends CrudRepository<Cupconsume, Integer> {
//	List<User> findAll();
//	User findByName(String name);
}