package com.user.registro.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.registro.models.dto.RegistroRequestModel;



@Entity
@Table(name = "registro")
public class Registro{


	@Id
	@Column(name = "idregistro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Column(name="pontoregistrado")
	private Date pontoregistrado; 
	
	@Column(name = "justificativa")
	private String justificativa; 
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User userid;
	
	public Registro() {
	}


	public Registro(String justificativa, User userid) {
		this.justificativa = justificativa;
		this.userid = userid;
	}

	public static Registro from(RegistroRequestModel registroRequestModel) {
		Registro registro = new Registro();
		registro.setPontoregistrado(registroRequestModel.getPointRegistry());
		registro.setJustificativa(registroRequestModel.getJustificativa());
		return registro;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getPontoregistrado() {
		return pontoregistrado;
	}


	public void setPontoregistrado(Date pontoregistrado) {
		this.pontoregistrado = pontoregistrado;
	}


	public String getJustificativa() {
		return justificativa;
	}


	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}


	public User getUserid() {
		return userid;
	}


	public void setUserid(User userid) {
		this.userid = userid;
	}
}
