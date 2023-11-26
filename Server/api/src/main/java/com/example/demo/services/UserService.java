package com.example.demo.services;

import com.example.demo.models.dtos.UserDTO;
import com.example.demo.models.entities.Token;
import com.example.demo.models.entities.User;

public interface UserService {

	User findOneById(Integer id);
	User findOneByIdentifier(String identifier);
	User login(String usernameOrEmail);
	User register(UserDTO info) throws Exception;
	User getUserByUsername(String username);
	
	//Token management
    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;
    
  //Find User authenticated
    User findUserAuthenticated();
	Boolean comparePassword(String toCompare, String current);
}
