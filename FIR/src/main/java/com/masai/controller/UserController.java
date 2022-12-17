package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.FirNotFound;
import com.masai.exception.LoginException;
import com.masai.exception.UserNotFound;
import com.masai.model.Fir;
import com.masai.model.User;
import com.masai.model.UserDTO;
import com.masai.service.UserService;

@RestController
@RequestMapping("/masaifir")
public class UserController {

	@Autowired
	private UserService uSer;
	

	@PostMapping("/user/register")
	public ResponseEntity<User> RegisterUser(@RequestBody User user) throws UserNotFound {

		return new ResponseEntity<User>(uSer.RegisterUser(user), HttpStatus.CREATED);

	}
	@PostMapping("/user/login")
	public ResponseEntity<String> LoginUser(@RequestBody UserDTO user) throws LoginException {

		return new ResponseEntity<String>(uSer.loginUser(user), HttpStatus.OK);
	}
	
	@GetMapping("/user/fir/{key}")
	public ResponseEntity<List<Fir>> getListOfDriver(@PathVariable("key") String key,@RequestBody Integer userid) throws FirNotFound, LoginException, UserNotFound {
		
		return new ResponseEntity<List<Fir>>(uSer.allFirOfUser(userid, key), HttpStatus.OK);
		
	}
	
	@DeleteMapping("user/fir/{firid}/{key}")
	public ResponseEntity<Fir> deleteBook(@PathVariable("firid") Integer firid,@PathVariable("key") String key ) throws FirNotFound, LoginException  {

		return new ResponseEntity<Fir>(uSer.withdrawFir(firid, key), HttpStatus.OK);

	}
}
