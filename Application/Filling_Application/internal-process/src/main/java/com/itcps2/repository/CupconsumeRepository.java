package com.itcps2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.itcps2.filling.model.Cupconsume;


public interface CupconsumeRepository extends CrudRepository<Cupconsume, Integer> {
//	List<User> findAll();
//	User findByName(String name);
}