package com.SoubraTeam.GConge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SoubraTeam.GConge.domain.User;
import com.SoubraTeam.GConge.exceptions.UsernameExistException;
import com.SoubraTeam.GConge.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	public User saveUser(User newUser) {
		
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));	
			newUser.setConfirmPassword(null);
			return userRepository.save(newUser);
		} catch (Exception e) {
			throw new UsernameExistException("Username "+newUser.getUsername()+" déja exist");
		}
		
		
	}
	
	
	
	
	
}
