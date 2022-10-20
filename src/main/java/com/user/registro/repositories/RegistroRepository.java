package com.user.registro.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.registro.models.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {	
	
	@Query(value = "select * from registro where user_id = user_id and convert(pontoregistrado, date) = convert(sysdate(), date)", nativeQuery = true)
	Collection<Registro> findUserIdByDay(Long userid );
	
	@Query(value = "select COUNT(*) from registro where convert(pontoregistrado, date) = convert(sysdate(), date)", nativeQuery = true)
	long RegistriesByDay(Long user_id);
}
