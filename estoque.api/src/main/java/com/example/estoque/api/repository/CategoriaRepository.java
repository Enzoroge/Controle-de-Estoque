package com.example.estoque.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estoque.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	
	List<Categoria> findByNome(String nome);

	
	

}
