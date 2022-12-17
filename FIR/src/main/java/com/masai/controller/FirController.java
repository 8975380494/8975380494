package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.FirNotFound;
import com.masai.exception.UserNotFound;
import com.masai.model.Fir;
import com.masai.model.User;
import com.masai.service.FirService;

@RestController
@RequestMapping("/masaifir")
public class FirController {
 
	@Autowired
	public FirService fser;
	
	@PostMapping("/user/fir")
	public ResponseEntity<Fir> RegisterUser(@RequestBody Fir fir) throws  FirNotFound {

		return new ResponseEntity<Fir>(fser.RegisterFir(fir), HttpStatus.CREATED);

	}
	
}
