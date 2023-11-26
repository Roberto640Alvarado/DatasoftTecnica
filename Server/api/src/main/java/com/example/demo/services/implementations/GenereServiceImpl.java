package com.example.demo.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.entities.Generes;
import com.example.demo.repository.GenereRepository;
import com.example.demo.services.GeneresService;


@Service
public class GenereServiceImpl implements GeneresService {
	
	@Autowired
	private GenereRepository generesRepository;

	@Override
	public List<Generes> getAll() {
		return generesRepository.findAll();
	}

	@Override
	public Generes findOneById(Integer id) {
		try {
			return generesRepository.findById(id)
					.orElse(null);
		} catch (Exception e) {
			return null;
	    }
	}

}
