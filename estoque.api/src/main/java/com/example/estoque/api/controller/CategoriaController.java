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

import com.example.estoque.api.model.Categoria;
import com.example.estoque.api.repository.CategoriaRepository;
import com.example.estoque.api.service.CategoriaService;
import com.example.estoque.api.service.ControllerrNotFoundException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CategoriaRepository CategoriaRepository;

	@GetMapping
	public List<Categoria> lista() {
		return CategoriaRepository.findAll();

	}

	@PostMapping
	public ResponseEntity<Categoria> criar(@RequestBody Categoria Categoria, HttpServletResponse response) {
		Categoria categoriasalvo = CategoriaRepository.save(Categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{codigo}")
				.buildAndExpand(categoriasalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(categoriasalvo);

	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		Categoria categoria = CategoriaRepository.findById(codigo).orElse(null);
		return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();

	}
	
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @RequestBody Categoria obj)
			throws ControllerrNotFoundException{
		obj = categoriaService.atualizarCategoria(codigo, obj);
		return ResponseEntity.ok().body(obj);
		
	}
}
