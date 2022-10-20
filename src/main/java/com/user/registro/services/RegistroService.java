package com.user.registro.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.registro.exceptions.UserNotFoundException;
import com.user.registro.models.Registro;
import com.user.registro.repositories.RegistroRepository;

@Service
public class RegistroService {

	
	private final RegistroRepository registroRepository;

	@Autowired
	public RegistroService(RegistroRepository registroRepository) {
		super();
		this.registroRepository = registroRepository;
	}
	
	
	public Registro save (Registro registro) {		
		return registroRepository.save(registro);
	}


	public List<Registro> getRegistros(){
        return StreamSupport
                .stream(registroRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

	 public Registro getRegistro(Long id){
	        return registroRepository.findById(id).orElseThrow(() ->
	                new UserNotFoundException(id));
	    }

	
	public Registro deleteRegistro(Long id) {
		Registro registro = getRegistro(id);
		registroRepository.delete(registro);	
		return registro;
	}

	@Transactional 
	public Registro editRegistro(Long id, Registro registro) {
		Registro registroToEdit = getRegistro(id);
		return registroRepository.save(registroToEdit);
	}
	
	public Collection <Registro> findUserIdByDay(Long userId) {
		return registroRepository.findUserIdByDay(userId);
	}
	
	public long RegistriesByDay(Long user_id) { 
		return registroRepository.RegistriesByDay(user_id);
	}
}
