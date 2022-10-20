package com.user.registro.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.user.registro.models.Gerente;
import com.user.registro.models.dto.GerenteRequestModel;
import com.user.registro.services.GerenteService;



@RequestMapping("/gerente") 
@RestController 
public class GerenteController {
	
	
	private final GerenteService gerenteService;

	@Autowired
	public GerenteController(GerenteService gerenteService) {
		this.gerenteService = gerenteService;
	}
	
	@GetMapping
    public ResponseEntity<List<GerenteRequestModel>> getGerente(){
        List<Gerente> gerentes = gerenteService.getGerente();
        List<GerenteRequestModel> gerenteRequestModels = gerentes.stream().map(GerenteRequestModel::from).collect(Collectors.toList());
        return new ResponseEntity<>(gerenteRequestModels, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<GerenteRequestModel> getGerente(@PathVariable final Long id){
        Gerente gerente = gerenteService.getGerente(id);
        return new ResponseEntity<>(GerenteRequestModel.from(gerente), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<GerenteRequestModel> deleteGerente(@PathVariable final Long id){
    	Gerente gerente = gerenteService.deleteGerente(id);
        return new ResponseEntity<>(GerenteRequestModel.from(gerente), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<GerenteRequestModel> editCart(@PathVariable final Long id,
                                            @RequestBody final GerenteRequestModel gerenteRequestModel){
    	Gerente gerente = gerenteService.editGerente(id, Gerente.from(gerenteRequestModel));
        return new ResponseEntity<>(GerenteRequestModel.from(gerente), HttpStatus.OK);
    }

	@PostMapping(value="/inserir")
    public ResponseEntity<GerenteRequestModel>  addGerente(@RequestBody @Valid final GerenteRequestModel gerenteRequestModel){
        Gerente gerente = gerenteService.save(Gerente.from(gerenteRequestModel));
        return new ResponseEntity<>(GerenteRequestModel.from(gerente), HttpStatus.OK);
    }


	@PostMapping(value = "{gerenteId}/user/{userId}/add")
		public ResponseEntity<GerenteRequestModel> addUserToGerente(@PathVariable("gerenteId") final Long gerenteId,
																	@PathVariable("userId") final Long userId){
		Gerente gerente = gerenteService.addUserToGerente(gerenteId, userId);
		return new ResponseEntity<>(GerenteRequestModel.from(gerente), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "{gerenteId}/user/{userId}/remove")
	public ResponseEntity<GerenteRequestModel> removeUserToGerente(@PathVariable final Long gerenteId,
																@PathVariable final Long userId){
	Gerente gerente = gerenteService.removeUserToGerente(gerenteId, userId);
	return new ResponseEntity<>(GerenteRequestModel.from(gerente), HttpStatus.OK);
	}
}

