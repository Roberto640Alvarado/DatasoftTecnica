package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dtos.LoginDTO;
import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.models.dtos.TokenDTO;
import com.example.demo.models.dtos.UserDTO;
import com.example.demo.models.entities.Token;
import com.example.demo.models.entities.User;
import com.example.demo.services.UserService;
import com.example.demo.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	private RequestErrorHandler errorHandler;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO loginDTO, BindingResult validations) {
		if (validations.hasErrors()) {
            return new ResponseEntity<>(
                    errorHandler.mapErrors(validations.getFieldErrors()),
                    HttpStatus.BAD_REQUEST);
        }

		User user = userService.findOneByIdentifier(loginDTO.getIdentifier());

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if(!userService.comparePassword(loginDTO.getPassword(),user.getPassword())) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	
		try {
			Token token = userService.registerToken(user);
			return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
			} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PostMapping("/signup") /* Implementation Register with Google*/
    public ResponseEntity<?> signUpUser(@RequestBody UserDTO register, BindingResult validations) {
        if (validations.hasErrors()) {
            return new ResponseEntity<>(
                    errorHandler.mapErrors(validations.getFieldErrors()),
                    HttpStatus.BAD_REQUEST);
        }

		String username = register.getUsername();

        if (userService.findOneByIdentifier(username) != null) {
            return new ResponseEntity<>(
                    new MessageDTO("Username or email already exists"),
                    HttpStatus.BAD_REQUEST);
        }

        try {
        	User registeredUser = userService.register(register);
            
            return new ResponseEntity<>(
                    new MessageDTO("User created"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new MessageDTO("Internal Server Error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
