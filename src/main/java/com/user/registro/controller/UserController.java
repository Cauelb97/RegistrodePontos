package com.user.registro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.registro.models.User;
import com.user.registro.models.dto.UserRequestModel;
import com.user.registro.services.UserService;

@RestController 
@RequestMapping("/userregistro") 
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService){	
	
	this.userService = userService;
}	
	
	@GetMapping("/all")
	public List<User> findAll(){
		return userService.getUsers();
	}
	
	@GetMapping(value = "{id}")
    public ResponseEntity<UserRequestModel> getUser(@PathVariable final Long id){
        User user = userService.getUser(id);
        return new ResponseEntity<>(UserRequestModel.from(user), HttpStatus.OK);
    }
	
	
	@PostMapping(value="/inserir")
    public ResponseEntity<UserRequestModel> addUser(@RequestBody @Valid final UserRequestModel userRequestModel){
        User user = userService.save(User.from(userRequestModel));
        return new ResponseEntity<>(UserRequestModel.from(user), HttpStatus.OK);
    }

	
	 @DeleteMapping(value = "{id}")
	 public ResponseEntity<UserRequestModel> deleteUser(@PathVariable final Long id){
	        User user = userService.deleteUser(id);
	        return new ResponseEntity<>(UserRequestModel.from(user), HttpStatus.OK);
	  }
	 
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "{id}")
	public ResponseEntity<UserRequestModel> editUser(@PathVariable final Long id,
													@RequestBody final UserRequestModel userRequestModel){
			User user = userService.editUser(id, User.from(userRequestModel));
			return new ResponseEntity<>(UserRequestModel.from(user), HttpStatus.OK);
	}
	
	@PostMapping(value = "{userId}/user/{registroId}/add")
	public ResponseEntity<UserRequestModel> addRegistroToUser(@PathVariable("userId") final Long userId,
																@PathVariable("registroId") final Long registroId){
	User user = userService.addRegistroToUser(userId, registroId);
	return new ResponseEntity<>(UserRequestModel.from(user), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "{userId}/user/{registroId}/remove")
	public ResponseEntity<UserRequestModel> removeRegistroToUser(@PathVariable("userId") final Long userId,
			@PathVariable("registroId") final Long registroId){
			User user = userService.removeRegistroToUser(userId, registroId);
				return new ResponseEntity<>(UserRequestModel.from(user), HttpStatus.OK);
	}
}

