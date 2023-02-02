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

	public Produto inserir(Produto obj) {

		obj.setValorTotal(obj.getQuantidade() * obj.getValor());

		return produtoRepository.save(obj);

	}

	private void produtoInserido(Produto produto, Produto obj) {
		produto.setQuantidade(obj.getQuantidade());
		produto.setNome(obj.getNome());
		produto.setValorTotal(obj.getValorTotal());
	}

	public Produto atualizarQuantidade(Long id, Produto obj) throws ControllerrNotFoundException {
		try {
			Produto entity = produtoRepository.getReferenceById(id);
			produtoAtualizado(entity, obj);
			return produtoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ControllerrNotFoundException(id);
		}

	}

	private void produtoAtualizado(Produto produto, Produto obj) {
		produto.setValor(obj.getValor());
		produto.setQuantidade(obj.getQuantidade());
		produto.setNome(obj.getNome());
		produto.setValorTotal(produto.getQuantidade() * produto.getValor());

	}

}
