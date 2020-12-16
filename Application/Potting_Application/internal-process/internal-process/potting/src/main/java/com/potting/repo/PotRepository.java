package com.potting.repo;

import org.springframework.data.repository.CrudRepository;

import com.pottin.model.Pot;

public interface PotRepository extends CrudRepository<Pot, Integer> {
//		List<User> findAll();
//		User findByName(String name);
}
