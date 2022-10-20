package com.user.registro.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.registro.exceptions.GerenteNotFoundException;
import com.user.registro.exceptions.UserIsAlreadyAssingnedException;
import com.user.registro.models.Gerente;
import com.user.registro.models.User;
import com.user.registro.repositories.GerenteRepository;


@Service
public class GerenteService {
	
	final GerenteRepository gerenteRepository;
	final UserService userService;

	@Autowired
	public GerenteService(GerenteRepository gerenteRepository, UserService userService) {
		this.gerenteRepository = gerenteRepository;
		this.userService = userService;
	}
	

	@Transactional 
	public Gerente save (Gerente gerente) {		
		return gerenteRepository.save(gerente);
	}

	 public List<Gerente> getGerente(){
	        return StreamSupport
	                .stream(gerenteRepository.findAll().spliterator(), false)
	                .collect(Collectors.toList());
	    }

	public Gerente getGerente(Long id){
        return gerenteRepository.findById(id).orElseThrow(() ->
                new GerenteNotFoundException(id));
    }
	
	@Transactional 
	public Gerente editGerente(Long id, Gerente gerente) {
		Gerente gerenteToEdit = getGerente(id);
		gerenteToEdit.setSerialNumber(gerente.getSerialNumber());
		return gerenteToEdit;
	}

	public Gerente deleteGerente(Long id) {
		Gerente gerente = getGerente(id);
		gerenteRepository.delete(gerente);	
		return gerente;
	}
	
	@Transactional
	public Gerente addUserToGerente (Long gerenteId, Long userId) {
		Gerente gerente = getGerente(gerenteId);
		User user = userService.getUser(userId);
		if (Objects.nonNull(user.getGerenteid())) {
			throw new UserIsAlreadyAssingnedException(userId, user.getGerenteid().getId());
		}
		gerente.addUser(user);
		user.setGerenteid(gerente);
		return gerente;
	}
	
	@Transactional
	public Gerente removeUserToGerente (Long gerenteId, Long userId) {
		Gerente gerente = getGerente(gerenteId);
		User user = userService.getUser(userId);
		gerente.removeUser(user);
		return gerente;
	}

}
