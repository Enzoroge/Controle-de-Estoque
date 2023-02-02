package com.example.estoque.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estoque.api.model.Categoria;
import com.example.estoque.api.repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria inserir(Categoria obj) {
		return categoriaRepository.save(obj);

	}

	private void categoriaInserido(Categoria categoria, Categoria obj) {
		categoria.setNome(obj.getNome());
	}

	public Categoria atualizarCategoria(Long id, Categoria obj) throws ControllerrNotFoundException {
		try {
			Categoria entity = categoriaRepository.getReferenceById(id);
			categoriaAtualizado(entity, obj);
			return categoriaRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ControllerrNotFoundException(id);
		}

	}

	private void categoriaAtualizado(Categoria categoria, Categoria obj) {
		categoria.setNome(obj.getNome());

	}

}
