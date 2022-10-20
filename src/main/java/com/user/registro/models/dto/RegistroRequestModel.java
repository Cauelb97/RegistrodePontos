package com.user.registro.models.dto;

import java.util.Date;
import java.util.Objects;

import com.user.registro.models.Registro;

public class RegistroRequestModel {

	private Date pointRegistry;
	private String justificativa;	
	
	private UserRequestModel user;

	public RegistroRequestModel() {}

	public RegistroRequestModel(Date pointRegistry, String justificativa, UserRequestModel user) {
		this.pointRegistry = pointRegistry;
		this.justificativa = justificativa;
		this.user = user;
	}
	
	public static RegistroRequestModel from(Registro registro) {
		RegistroRequestModel registroRequestModel = new RegistroRequestModel();
		registroRequestModel.setPointRegistry(registro.getPontoregistrado());
		registroRequestModel.setJustificativa(registro.getJustificativa());
		if(Objects.nonNull(registro.getUserid())){
            registroRequestModel.setUser(UserRequestModel.from(registro.getUserid()));
        }
		return registroRequestModel;
	}

	public Date getPointRegistry() {
		return pointRegistry;
	}

	public void setPointRegistry(Date pointRegistry) {
		this.pointRegistry = pointRegistry;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public UserRequestModel getUser() {
		return user;
	}

	public void setUser(UserRequestModel user) {
		this.user = user;
	}
}
