package com.SoubraTeam.GConge.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.SoubraTeam.GConge.domain.TA_Conges;
import com.SoubraTeam.GConge.services.MapValidationErrorService;
import com.SoubraTeam.GConge.services.TA_CongeService;

@RestController
@RequestMapping("/api/CongesType")
public class TA_CongesController {

	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	@Autowired
	private TA_CongeService ta_CongeService;
	
	@PostMapping("")
	public ResponseEntity<?> CreateNewCongesType(@Valid @RequestBody TA_Conges ta_Conge, BindingResult result){	
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationError(result);
		if (errorMap != null) return errorMap;
		
		TA_Conges ta_Conge1 = ta_CongeService.saveOrUpdateTaConges(ta_Conge);
		return new ResponseEntity<TA_Conges>(ta_Conge1, HttpStatus.CREATED);
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<?> getTACongeByCode(@PathVariable String code){
		TA_Conges ta_conge = ta_CongeService.findByTACongeCode(code);
		return new ResponseEntity<TA_Conges>(ta_conge, HttpStatus.OK);
	}
	@GetMapping("/all")
	public Iterable<TA_Conges> getAllTAConges(){
		return ta_CongeService.findAllTAConges();
	}
	
	@DeleteMapping("/{code}")
	public ResponseEntity<?> deleteTAConge(@PathVariable String code){
		ta_CongeService.deleteTACongeByCode(code);
		return new ResponseEntity<String>("La famille de congés '"+code+"' a été supprimer.", HttpStatus.OK);
	}
}
