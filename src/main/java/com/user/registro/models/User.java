package com.user.registro.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.registro.models.dto.UserRequestModel;



@Entity
@Table(name = "user")
public class User implements UserDetails, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="cargo")
	private String cargo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "gerente_idgerente") 
	private Gerente gerenteid; 
	
	@JsonIgnore
	@OneToMany(mappedBy = "id")
	@Cascade(CascadeType.ALL)
	private List<Registro> registros = new ArrayList<>();
	
	
	
	
	public User(long id, String username, String cargo, String email, String password, Gerente gerenteid) { //, List<Registro> registro
		super();
		this.id = id;
		this.cargo = cargo;
		this.email = email;
		this.password = password;
		this.username = username;
//		this.registro = registro;
		this.gerenteid = gerenteid;
	}


	public User() {}
	
	public void addRegistro(Registro registro) {
		registros.add(registro);
	}
	
	public void removeRegistro(Registro registro) {
		registros.remove(registro);
	}

	public static User from(UserRequestModel userRequestModel) {
		User user = new User();
		user.setUsername(userRequestModel.getUsername());
		user.setCargo(userRequestModel.getCargo());
		user.setEmail(userRequestModel.getEmail());
		user.setPassword(userRequestModel.getPassword());
		return user;
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


	public Gerente getGerenteid() {
		return gerenteid;
	}


	public void setGerenteid(Gerente gerenteid) {
		this.gerenteid = gerenteid;
	}


	public List<Registro> getRegistro() {
		return registros;
	}


	public void setRegistro(List<Registro> registro) {
		this.registros = registro;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
