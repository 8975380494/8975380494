package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.FirNotFound;
import com.masai.exception.LoginException;
import com.masai.exception.UserNotFound;
import com.masai.model.CurrentUserSession;
import com.masai.model.Fir;
import com.masai.model.User;
import com.masai.model.UserDTO;
import com.masai.repository.FirDAO;
import com.masai.repository.SessionDAO;
import com.masai.repository.UserDAO;

import net.bytebuddy.utility.RandomString;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserDAO udao;
	
	@Autowired
	private FirDAO fdao;
	
	@Autowired
	private SessionDAO sdao;
	
	@Override
	public User RegisterUser(User user) throws UserNotFound {
		// TODO Auto-generated method stub
		List<User> users = udao.findAll();

		if (users.isEmpty())
			throw new UserNotFound("No user records found");
		
		for(User i:users ) {
			if(user.getUserid() == i.getUserid()) {
				throw new UserNotFound(" user already registered with the userId");
			}
		}
		return udao.save(user);
	}

	@Override
	public String loginUser(UserDTO dto) throws LoginException {
		// TODO Auto-generated method stub
		Optional<User> us = udao.findById(dto.getUserId());
	    
		User user = us.get();
		if(user==null)
		{
			throw new LoginException("Please Enter valid Email");
		}
		
		Optional<CurrentUserSession> opt = sdao.findById(user.getUserid());
		
		if(opt.isPresent())
		{
			throw new LoginException("User Already logged in ");
		}
		
		if(user.getPassword().equals(dto.getPassword()))
		{
			String key = RandomString.make(7);
			
			CurrentUserSession cus = new CurrentUserSession(user.getUserid(),key,LocalDateTime.now());
			
			sdao.save(cus);
			
			return "Login succesfully , Unique key :  "+key+" , And details : "+user.toString();
		}
		else
		{
			throw new LoginException("Please Enter a valid password.");
		}
	}

	@Override
	public List<Fir> allFirOfUser(Integer userid,String key) throws FirNotFound, LoginException, UserNotFound {
		// TODO Auto-generated method stub
		
		CurrentUserSession  loggedUser= sdao.findByUuid(key);
		
		if(loggedUser==null)
		{
			throw new LoginException("Invalid user key enter valid user key");
		}
		
		Optional<User>  us= udao.findById(userid);
		if(us == null) {
			throw new UserNotFound("Invalid userid");
		}

		User user = us.get();
	  if(user.getFir() ==null) {
		  throw new FirNotFound("User has Never Filed Any fir");
	  }
		List<Fir> list = user.getFir();
		
		return list;
	}

	@Override
	public Fir withdrawFir(Integer firid,String key) throws FirNotFound, LoginException {
		// TODO Auto-generated method stub
       CurrentUserSession  loggedUser= sdao.findByUuid(key);
		if(loggedUser==null)
		{
			throw new LoginException("Invalid admin key ,you are not admin");
		}
		
		
		Optional<Fir> found = fdao.findById(firid);		
		
		
		if(found.isPresent()) {
			Fir fir =found.get();
			fdao.delete(fir);
			return fir;
		}
		else
			throw  new FirNotFound("Please enter valid firid to withdraw fir");
	}

}
