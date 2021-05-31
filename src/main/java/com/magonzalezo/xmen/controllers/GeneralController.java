package com.magonzalezo.xmen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magonzalezo.xmen.dtos.DnaDto;
import com.magonzalezo.xmen.dtos.StatOutcomeDto;
import com.magonzalezo.xmen.services.IMutantService;
import com.magonzalezo.xmen.services.IStatService;

@RestController
@RequestMapping("/")
public class GeneralController {
	
	@Autowired
	private IMutantService mutantService; 
	
	@Autowired
	private IStatService statService;
	
	@PostMapping(
			value="/mutant",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isMutant(@RequestBody DnaDto dnaDto) {
		if (mutantService.isMutant(dnaDto))
			return new ResponseEntity<>(true, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping(
			value="/stats",
			produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<StatOutcomeDto> returnStats() {
		return new ResponseEntity<>(statService.returnStats(), HttpStatus.OK);
	}

}
