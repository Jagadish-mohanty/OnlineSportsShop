package com.capg.onlinesportsshopee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.onlinesportsshopee.bean.User;
import com.capg.onlinesportsshopee.exceptions.LoginNotFoundException;
import com.capg.onlinesportsshopee.exceptions.UserNotFoundException;
import com.capg.onlinesportsshopee.model.UserDTO;



@Service
public class LoginServiceImpl implements ILoginService {

	
	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean Login(User user) throws LoginNotFoundException,UserNotFoundException {
		boolean flag = false;
		if(user == null)
			//throw new LoginNotFoundException("User Details cannot be Empty");
			flag= false;
		else if(!userService.checkUser(user.getUserId(), user.getUsername(), user.getPassword()))
			//throw new LoginNotFoundException("Invalid UserName or Password");
			flag= false;
		else
			flag = true;
		return flag;
	}
	
	@Override
	public User GetLogin(User user) throws LoginNotFoundException,UserNotFoundException {
		//boolean flag = false;
		UserDTO udto=null;
		udto=userService.getId(user.getUserId());
		User userprop=new User();
		 if (udto==null) {
			throw new LoginNotFoundException("Invalid UserName or Password");
		}	//flag= false;
		else {
			userprop.setPassword(udto.getPassword());
			userprop.setRole(udto.getRole());
			userprop.setUserId(udto.getUserId());
			userprop.setUsername(udto.getusername());
		}
			//flag = true;
		return userprop;
	}

}