package com.user.registro.models.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.user.registro.models.User;

public class UserRequestModel {

	
	private long id;
	
	private String username;
	
	private String cargo;
		
	@Email
	private String email;
	
	@Size(min=8,max=16, message="Password must be equal or greater than 8 characters and less than 16 characters")
	private String password;
	
	private GerenteRequestModel gerenteRequestModel;
	
	private List<RegistroRequestModel> registroRequestModels = new ArrayList<>();
	
	public static UserRequestModel from(User user) {
		UserRequestModel userRequestModel = new UserRequestModel();
		userRequestModel.setId(user.getId());
		userRequestModel.setUsername(user.getUsername());
		userRequestModel.setCargo(user.getCargo());
		userRequestModel.setEmail(user.getEmail());
		userRequestModel.setPassword(user.getPassword());
		userRequestModel.setRegistroRequestModels(user.getRegistro().stream().map(RegistroRequestModel::from).collect(Collectors.toList()));
		if(Objects.nonNull(user.getGerenteid())){
            userRequestModel.setGerenteRequestModel(GerenteRequestModel.from(user.getGerenteid()));
        }
		return userRequestModel;
		
	}

	public UserRequestModel() {}


	public UserRequestModel(long id, String username, String cargo,  String email,
			String password) {
		this.id = id;
		this.username = username;
		this.cargo = cargo;
		this.email = email;
		this.password = password;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GerenteRequestModel getGerenteRequestModel() {
		return gerenteRequestModel;
	}

	public void setGerenteRequestModel(GerenteRequestModel gerenteRequestModel) {
		this.gerenteRequestModel = gerenteRequestModel;
	}

	public List<RegistroRequestModel> getRegistroRequestModels() {
		return registroRequestModels;
	}

	public void setRegistroRequestModels(List<RegistroRequestModel> registroRequestModels) {
		this.registroRequestModels = registroRequestModels;
	}
	
	 
}