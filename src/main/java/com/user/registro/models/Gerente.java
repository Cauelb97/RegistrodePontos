package com.user.registro.models;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.registro.models.dto.GerenteRequestModel;




@Entity
@Table(name = "gerente")
public class Gerente{


	private String serialNumber;
	@Id
	@Column(name = "idgerente")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id")
	@Cascade(CascadeType.ALL)
	private List<User> users = new ArrayList<>(); 

	public Gerente() { 
	}

	
	
	public Gerente(Long id, String nome, List<User> users) {
		super();
		this.id = id;
		this.nome = nome;
		this.users = users;
	}



	public void addUser(User user) {
		users.add(user);
	}
	
	public void removeUser(User user) {
		users.remove(user);
	}

	public static Gerente from(GerenteRequestModel gerenteRequestModel) {
		Gerente gerente = new Gerente();
		gerente.setNome(gerenteRequestModel.getNome());
		return gerente;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	
}
