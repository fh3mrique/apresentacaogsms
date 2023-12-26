package com.gcms.fluflycats.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcms.fluflycats.entities.Gato;
import com.gcms.fluflycats.repository.GatoRepository;

@RestController
@RequestMapping("/gatos")
@CrossOrigin(origins = "*")
public class GatoAPI {
	
	@Autowired
	private GatoRepository r;
	
	@GetMapping
	public ResponseEntity<List<Gato>> findall(){
		List<Gato> gatos = r.findAll();
		
		return ResponseEntity.ok().body(gatos);
	}

}
