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
import com.example.estoque.api.model.Produto;
import com.example.estoque.api.repository.ProdutoRepository;
import com.example.estoque.api.service.CategoriaService;
import com.example.estoque.api.service.ControllerrNotFoundException;
import com.example.estoque.api.service.ProdutoService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private CategoriaService categoriaService;
	 
	@Autowired
	private ProdutoService produtoService;

	@Autowired
	public ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> lista() {
		return produtoService.findAll();
	}

	@PostMapping
	public ResponseEntity<Produto> inserir(@RequestBody Produto produto, HttpServletResponse response ) {
		Produto produtoSalvo = produtoService.inserir(produto);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{codigo}")
			.buildAndExpand(produtoSalvo.getCodigo()).toUri();
	response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(produtoSalvo);

	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> buscarPeloCodigo(@PathVariable Long codigo) {
		Produto produto = produtoRepository.findById(codigo).orElse(null);
		return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();

	}

	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Produto> adcionar(@PathVariable Long codigo, @RequestBody Produto obj)
			throws ControllerrNotFoundException {
		obj = produtoService.atualizarQuantidade(codigo, obj);
		return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/{codigo}/{categoria}/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @RequestBody Categoria obj)
			throws ControllerrNotFoundException {
		obj = categoriaService.atualizarCategoria(codigo, obj);
		return ResponseEntity.ok().body(obj);
	}

}
