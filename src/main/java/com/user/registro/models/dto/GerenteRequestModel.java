package com.user.registro.models.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.user.registro.models.Gerente;

public class GerenteRequestModel {

	
	private Long id;
	private String nome;
	private List<UserRequestModel> usersRequestModel = new ArrayList<>();
	
	public static GerenteRequestModel from(Gerente gerente) {
		GerenteRequestModel gerenteRequestModel = new GerenteRequestModel();
		gerenteRequestModel.setId(gerente.getId());
		gerenteRequestModel.setNome(gerente.getNome());
		gerenteRequestModel.setUsersRequestModel(gerente.getUsers().stream().map(UserRequestModel::from).collect(Collectors.toList()));
		return gerenteRequestModel;
	}
	
	public GerenteRequestModel() {}

	public GerenteRequestModel(Long id, String nome, List<UserRequestModel> usersRequestModel) {
		this.id = id;
		this.nome = nome;
		this.usersRequestModel = usersRequestModel;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<UserRequestModel> getUsersRequestModel() {
		return usersRequestModel;
	}

	public void setUsersRequestModel(List<UserRequestModel> usersRequestModel) {
		this.usersRequestModel = usersRequestModel;
	}
}
