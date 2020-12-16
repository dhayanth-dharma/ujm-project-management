package com.itcps2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.itcps2.filling.model.User;
import com.itcps2.repository.UserRepository;

//@Service
//@Component
public class UserService {
    @Autowired
	private UserRepository ur;
	public Optional<User> getUser(int userID)
	{
		return ur.findById(userID);
		
	}

}
