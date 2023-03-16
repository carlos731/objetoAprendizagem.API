package com.senac.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.senac.api.entity.GrauDificuldade;
import com.senac.api.entity.ObjetoAprendizagem;
import com.senac.api.entity.Usuario;
import com.senac.api.request.ObjetoAprendizagemRequest;
import com.senac.api.response.ObjetoAprendizagemResponse;
import com.senac.api.service.ObjetoAprendizagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/objetoAprendizagem")
public class ObjetoAprendizagemController {

	@Autowired
	private ObjetoAprendizagemService objetoAprendizagemService;
	
	@PostMapping
	public ResponseEntity<ObjetoAprendizagemResponse> adicionar(@Valid @RequestBody ObjetoAprendizagemRequest objetoAprendizagemReq){
		ModelMapper mapper = new ModelMapper();
		ObjetoAprendizagem objetoAprendizagem = mapper.map(objetoAprendizagemReq, ObjetoAprendizagem.class);
		objetoAprendizagem = objetoAprendizagemService.adicionar(objetoAprendizagem);
		return new ResponseEntity<>(mapper.map(objetoAprendizagem, ObjetoAprendizagemResponse.class), HttpStatus.CREATED);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<ObjetoAprendizagemResponse> upload(
			@RequestParam("descricao") String descricao,
			@RequestParam("file") MultipartFile file,
			@RequestParam("grauDificuldadeId") GrauDificuldade grauDificuldadeId,
			@RequestParam("usuarioId") Usuario usuarioId
			) throws Exception{
		ModelMapper mapper = new ModelMapper();
		ObjetoAprendizagem objetoAprendizagem = new ObjetoAprendizagem();
		objetoAprendizagem.setDescricao(descricao);
		objetoAprendizagem.setBlob(file.getBytes());
		objetoAprendizagem.setStatus(true);
		objetoAprendizagem.setGrauDificuldadeId(grauDificuldadeId);
		objetoAprendizagem.setUsuarioId(usuarioId);
		
		objetoAprendizagem = objetoAprendizagemService.upload(file, objetoAprendizagem);
		
		return new ResponseEntity<>(mapper.map(objetoAprendizagem, ObjetoAprendizagemResponse.class), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ObjetoAprendizagemResponse>> obterTodos(){
		List<ObjetoAprendizagem> objetoAprendizagems = objetoAprendizagemService.obterTodos();
		ModelMapper mapper = new ModelMapper();
		List<ObjetoAprendizagemResponse> resposta = objetoAprendizagems.stream().map(objetoAprendizagem -> mapper.map(objetoAprendizagem, ObjetoAprendizagemResponse.class)).collect(Collectors.toList());
		return new ResponseEntity<>(resposta, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<ObjetoAprendizagemResponse>> obterPorId(@PathVariable Long id){
		Optional<ObjetoAprendizagem> objetoAprendizagemEncontrado = objetoAprendizagemService.obterPorId(id);
		ObjetoAprendizagemResponse objetoAprendizagem = new ModelMapper().map(objetoAprendizagemEncontrado.get(), ObjetoAprendizagemResponse.class);
		return new ResponseEntity<>(Optional.of(objetoAprendizagem), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ObjetoAprendizagemResponse> atualizar(@Valid @RequestBody ObjetoAprendizagemRequest objetoAprendizagemReq, @PathVariable Long id){
		ModelMapper mapper = new ModelMapper();
		ObjetoAprendizagem objetoAprendizagem = mapper.map(objetoAprendizagemReq, ObjetoAprendizagem.class);
		objetoAprendizagem = objetoAprendizagemService.atualizar(id, objetoAprendizagem);
		return new ResponseEntity<>(mapper.map(objetoAprendizagem, ObjetoAprendizagemResponse.class), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		objetoAprendizagemService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
