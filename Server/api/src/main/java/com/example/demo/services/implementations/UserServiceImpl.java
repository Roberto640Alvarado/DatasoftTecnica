package com.example.demo.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.dtos.UserDTO;
import com.example.demo.models.entities.Token;
import com.example.demo.models.entities.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.utils.JWTtools;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Autowired
	private JWTtools jwtTools;

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public User findOneById(Integer id) {
		try {
			return userRepository.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Token registerToken(User user) throws Exception {
		cleanTokens(user);
		String tokenString = jwtTools.generateToken(user);
		Token token = new Token(tokenString, user);
		tokenRepository.save(token);
		return token;
	}

	@Override
	public Boolean isTokenValid(User user, String token) {
		try {
			cleanTokens(user);
			List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
			tokens.stream().filter(tk -> tk.getContent().equals(token)).findAny().orElseThrow(() -> new Exception());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void cleanTokens(User user) throws Exception {
		List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
		tokens.forEach(token -> {
			if (!jwtTools.verifyToken(token.getContent())) {
				token.setActive(false);
				tokenRepository.save(token);
			}
		});
	}

	@Override
	public User findUserAuthenticated() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByUsername(username);
	}

	@Override
	public Boolean comparePassword(String toCompare, String current) {
		return passwordEncoder.matches(toCompare, current);
	}

	public User findOneByIdentifier(String identifier) {
		return userRepository.findByUsername(identifier);
	}

	@Override
	public User login(String username) {
		 User user = userRepository.findByUsername(username);
			if (user != null) {
				return user;
			}
			return null; 
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	/* Implementation Register with Google*/
	public User register(UserDTO info) throws Exception {
		User newUser = new User();

		newUser.setId(info.getId());
		newUser.setFullName(info.getFullName());
		newUser.setUsername(info.getUsername());
		newUser.setPassword(passwordEncoder.encode(info.getPassword()));

		userRepository.save(newUser);
		
		return newUser;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
