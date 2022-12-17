package com.masai.service;

import java.util.List;

import com.masai.exception.FirNotFound;
import com.masai.exception.LoginException;
import com.masai.exception.UserNotFound;
import com.masai.model.Fir;
import com.masai.model.User;
import com.masai.model.UserDTO;

public interface UserService {

	 public User RegisterUser(User user)  throws UserNotFound ;
	 
     public String loginUser(UserDTO dto) throws LoginException;
     
     public List<Fir> allFirOfUser(Integer userid,String key) throws FirNotFound, LoginException, UserNotFound;
     
     public Fir withdrawFir(Integer firid,String key) throws FirNotFound, LoginException;
}
