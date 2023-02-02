package com.example.estoque.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.estoque.api.model.Usuario;
import com.example.estoque.api.repository.UsuarioRepository;
import com.example.estoque.api.service.ControllerrNotFoundException;
import com.example.estoque.api.service.UsuarioService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository UsuarioRepository;

	@GetMapping
	public List<Usuario> lista() {
		return UsuarioRepository.findAll();

	}

	@PostMapping
	public ResponseEntity<Usuario> criar(@RequestBody Usuario Usuario, HttpServletResponse response) {
		Usuario usuariosalvo = UsuarioRepository.save(Usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{codigo}")
				.buildAndExpand(usuariosalvo.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(usuariosalvo);

	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long codigo) {
		Usuario Usuario = UsuarioRepository.findById(codigo).orElse(null);
		return Usuario != null ? ResponseEntity.ok(Usuario) : ResponseEntity.notFound().build();

	}
	
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @RequestBody Usuario obj)
			throws ControllerrNotFoundException{
		obj = usuarioService.atualizarUsuario(codigo, obj);
		return ResponseEntity.ok().body(obj);
		
	}
}
