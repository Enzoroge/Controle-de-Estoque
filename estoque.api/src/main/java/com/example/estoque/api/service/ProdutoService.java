package com.example.estoque.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estoque.api.model.Produto;
import com.example.estoque.api.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> findAll() {
		return produtoRepository.findAll();

	}
	
	public Produto adcionarQuantidade(Long id, Produto obj) throws ControllerrNotFoundException {
		try {
			Produto entity = produtoRepository.getReferenceById(id);
			produtoAdcionado(entity, obj);
			return produtoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ControllerrNotFoundException(id);
		}

	}

	
		
		
	

	private void produtoAdcionado(Produto produto, Produto obj) {
		produto.setQuantidade(obj.getQuantidade());
		

	}
	

}
