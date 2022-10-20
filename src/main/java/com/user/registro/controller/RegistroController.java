package com.user.registro.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

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

import com.user.registro.models.Registro;
import com.user.registro.models.dto.RegistroRequestModel;
import com.user.registro.services.RegistroService;

@RestController 
@RequestMapping("registro") 
public class RegistroController {

	private final RegistroService registroService;
	
	@Autowired
	public RegistroController(RegistroService registroService){	
	
	this.registroService = registroService;
	}	
	
	@GetMapping("/all")
	public List<Registro> findAll(){
		return registroService.getRegistros();
	}
	
	@GetMapping(value = "/usuario/dia/{user_id}")
	public ResponseEntity<Collection<Registro>> RegistriesByDay(@PathVariable(value = "user_id")Long user_id) {
		Collection <Registro> registriesByDay = registroService.findUserIdByDay(user_id);
		return ResponseEntity.ok().body(registriesByDay);		
	}			

	
	@GetMapping(value = "{id}")
    public ResponseEntity<RegistroRequestModel> getRegistro(@PathVariable final Long id){
        Registro registro = registroService.getRegistro(id);
        return new ResponseEntity<>(RegistroRequestModel.from(registro), HttpStatus.OK);
    }
	
	@GetMapping(value = "hours/{user_id}")
	public ResponseEntity<String> showHours(@PathVariable(value = "user_id")Long user_id) {
		Collection <Registro> regisDay = registroService.findUserIdByDay(user_id);
		long registryDay = regisDay.size();	
		Registro[] registries = regisDay.toArray(new Registro[0]);		
		long minutes = 0;	 
	    Date init_point = null, final_point = null;	
	    
	    for(int i = 0; i < registryDay; i++) {	
	    	if (registryDay % 2 == 0) { 
		        if(i % 2 == 0) {		        
		        	init_point = registries[i].getPontoregistrado();
		        }else{
		        	final_point = registries[i].getPontoregistrado();
		        	long diff = final_point.getTime() - init_point.getTime();
		        	minutes += TimeUnit.MILLISECONDS.toMillis(diff);			            	
		        }		      
		    }else{  
		    	if (registryDay > 2) {
	    			do {	    			
	    				if(i % 2 == 0) {		        
	    		        	init_point = registries[i].getPontoregistrado();
	    		        }else{
	    		        	final_point = registries[i].getPontoregistrado();
	    		        	long diff = final_point.getTime() - init_point.getTime();
	    		            minutes += TimeUnit.MILLISECONDS.toMillis(diff);
	    		        } 			    				
	    			}while(registryDay <= (registryDay-1));   	
		    	}else if (registryDay == 0){
		    		minutes = 0;
	    		}else {
	    			init_point = registries[i].getPontoregistrado();	 
	    			Calendar cal = Calendar.getInstance();
	    			Date now = cal.getTime();
	    			long diff = now.getTime() - init_point.getTime();
    		        minutes += TimeUnit.MILLISECONDS.toMillis(diff); 	    			
	    		}
		    }
		}
	    
	    long min  = ( minutes / 60000 ) % 60; 
        long hours    = minutes / 3600000;
	    String showH =  String.format("Voce trabalhou %02dh:%02dmin", hours, min);
	    	    
	    if (minutes < (480*60000)) {
    		System.out.println("Você tem que trablhar 8 horas por dia. " + showH + ", preencha a justificativa"); 
       	}
		return ResponseEntity.ok().body(showH);
	} 
	
	@PostMapping(value="/inserir")
    public ResponseEntity<RegistroRequestModel> addRegistro(@RequestBody @Valid final RegistroRequestModel registroRequestModel){
        Registro registro = registroService.save(Registro.from(registroRequestModel));
        return new ResponseEntity<>(RegistroRequestModel.from(registro), HttpStatus.OK);
    }

	
	 @DeleteMapping(value = "{id}")
	    public ResponseEntity<RegistroRequestModel> deleteRegistro(@PathVariable final Long id){
		 Registro registro = registroService.deleteRegistro(id);
	        return new ResponseEntity<>(RegistroRequestModel.from(registro), HttpStatus.OK);
	    }
	 
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "{id}")
	public ResponseEntity<RegistroRequestModel> editRegistro(@PathVariable final Long id,
													@RequestBody final RegistroRequestModel registroRequestModel){
		Registro registro = registroService.editRegistro(id, Registro.from(registroRequestModel));
			return new ResponseEntity<>(RegistroRequestModel.from(registro), HttpStatus.OK);
	}
}
