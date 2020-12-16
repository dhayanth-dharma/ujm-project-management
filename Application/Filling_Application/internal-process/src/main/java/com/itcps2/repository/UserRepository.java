package com.itcps2.repository;

import com.itcps2.filling.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



//public interface UserRepository extends JpaRepository<User, Long> {
public interface UserRepository extends CrudRepository<User, Integer> {
//	List<User> findAll();
//	User findByName(String name);
}
