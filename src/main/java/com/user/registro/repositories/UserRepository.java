package com.user.registro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.registro.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String name);
}