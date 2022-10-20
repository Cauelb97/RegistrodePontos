package com.user.registro.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.registro.exceptions.RegistroAlreadyAssingedException;
import com.user.registro.exceptions.UserNotFoundException;
import com.user.registro.models.Registro;
import com.user.registro.models.User;
import com.user.registro.repositories.UserRepository;


@Service
public class UserService {

	private final UserRepository userRepository;
	private final RegistroService registroService;

	@Autowired
	public UserService(UserRepository userRepository, RegistroService registroService) {
		super();
		this.userRepository = userRepository;
		this.registroService = registroService;
	}
	
	
	public User save (User user) {		
		return userRepository.save(user);
	}


	public List<User> getUsers(){
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

	 public User getUser(Long id){
	        return userRepository.findById(id).orElseThrow(() ->
	                new UserNotFoundException(id));
	    }

	
	public User deleteUser(Long id) {
		User user = getUser(id);
		userRepository.delete(user);	
		return user;
	}

	@Transactional 
	public User editUser(Long id, User user) {
		User userToEdit = getUser(id);
		return userRepository.save(userToEdit);
	}
	
	@Transactional
	public User addRegistroToUser (long userId, long registroId) {
		User user = getUser(userId);
		Registro registro = registroService.getRegistro(registroId);
		user.addRegistro(registro);
		registro.setUserid(user);
		return user;
	}
	
	@Transactional
	public User removeRegistroToUser (long userId, long registroId) {
		User user = getUser(userId);
		Registro registro = registroService.getRegistro(registroId);
		user.removeRegistro(registro);
		return user;
	}
}
