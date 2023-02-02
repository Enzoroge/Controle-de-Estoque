package com.example.estoque.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estoque.api.model.Produto;
import com.example.estoque.api.model.Usuario;
import com.example.estoque.api.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario inserir(Usuario obj) {
		return usuarioRepository.save(obj);

	}
	private void usuarioInserido(Usuario usuario, Usuario obj) {
		usuario.setCpf(obj.getCpf());
		usuario.setEmail(obj.getEmail());
		usuario.setNome(obj.getNome());
	}

	public Usuario atualizarUsuario(Long id, Usuario obj) throws ControllerrNotFoundException {
		try {
			Usuario entity = usuarioRepository.getReferenceById(id);
			UsuarioAtualizado(entity, obj);
			return usuarioRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ControllerrNotFoundException(id);
		}

	}

	private void UsuarioAtualizado(Usuario usuario, Usuario obj) {
		usuario.setCpf(obj.getCpf());
		usuario.setEmail(obj.getEmail());
		usuario.setNome(obj.getNome());

	}

}
