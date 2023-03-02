package com.example.estoque.api.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estoque.api.model.Categoria;
import com.example.estoque.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	

}
