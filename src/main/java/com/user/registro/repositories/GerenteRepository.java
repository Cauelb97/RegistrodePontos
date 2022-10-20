package com.user.registro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.registro.models.Gerente;


@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {

	
}
