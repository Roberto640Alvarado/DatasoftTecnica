package com.example.demo.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "generes")
public class Generes {
	
	@Id
	 @Column(name = "id")
	 private Integer id;
	 
	 @Column(name = "name")
	 private String name;
	 
	 @OneToMany(mappedBy = "generes",fetch = FetchType.LAZY)
	 @JsonIgnore
	 private List<Books> booksGeneres; //conexion con books
	 
	 public Generes(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

}
