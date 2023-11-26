package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.entities.Token;
import com.example.demo.models.entities.User;



public interface TokenRepository extends JpaRepository<Token, UUID>{
	List<Token> findByUserAndActive(User user, Boolean active);

}