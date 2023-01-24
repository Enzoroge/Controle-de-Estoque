package com.example.estoque.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.estoque.api.model.Produto;
import com.example.estoque.api.repository.ProdutoRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> lista() {
		return produtoRepository.findAll();

	}
	
	/*@PostMapping
	public ResponseEntity<Produto>criar(@RequestBody Produto produto, HttpServletResponse response) {
		Produto produtoSalvo = produtoRepository.save(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{codigo}")
				.buildAndExpand(produtoSalvo.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(produtoSalvo);
		
		
	}*/

	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> buscarPeloCodigo(@PathVariable Long codigo) {
		Produto produto = produtoRepository.findById(codigo).orElse(null);
		return produto != null ? ResponseEntity.ok(produto): ResponseEntity.notFound().build();
	
	
}
}
