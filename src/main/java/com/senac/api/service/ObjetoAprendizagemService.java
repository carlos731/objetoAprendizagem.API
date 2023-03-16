package com.senac.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.senac.api.entity.ObjetoAprendizagem;
import com.senac.api.exception.ObjectnotFoundException;
import com.senac.api.repository.ObjetoAprendizagemRepository;

@Service
public class ObjetoAprendizagemService {

	@Autowired
	private ObjetoAprendizagemRepository objetoAprendizagemRepository;
	
	public ObjetoAprendizagem adicionar(ObjetoAprendizagem objetoAprendizagem) {
		objetoAprendizagem.setId(null);
		objetoAprendizagem = objetoAprendizagemRepository.save(objetoAprendizagem);
		objetoAprendizagem.setId(objetoAprendizagem.getId());
		return objetoAprendizagem;
	}
	
	public ObjetoAprendizagem upload(MultipartFile file, ObjetoAprendizagem objetoAprendizagem) throws Exception {
		   String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	       
	       try {
	            if(fileName.contains("..")) {
	                throw  new Exception("Filename contains invalid path sequence " + fileName);
	            }

	            return objetoAprendizagemRepository.save(objetoAprendizagem);

	       } catch (Exception e) {
	            throw new Exception("Could not save File: " + fileName);
	       }
	}

	
	public List<ObjetoAprendizagem> obterTodos(){
		List<ObjetoAprendizagem> objetoAprendizagems = objetoAprendizagemRepository.findAll();
		return objetoAprendizagems;
	}
	
	public Optional<ObjetoAprendizagem> obterPorId(Long id){
		Optional<ObjetoAprendizagem> objetoAprendizagem = objetoAprendizagemRepository.findById(id);
		
		if(objetoAprendizagem.isEmpty()) {
			throw new ObjectnotFoundException("OBJETO DE APRENDIZAGEM COM ID : '" + id + "' NÃO ENCONTRADO!");
		}
		
		return objetoAprendizagem;
	}
	
	public ObjetoAprendizagem atualizar(Long id, ObjetoAprendizagem objetoAprendizagem) {
		Optional<ObjetoAprendizagem> objetoAprendizagemId = objetoAprendizagemRepository.findById(id);
		
		if(objetoAprendizagemId.isEmpty()) {
			throw new ObjectnotFoundException("OBJETO DE APRENDIZAGEM COM ID: '" + id + "' NÃO ENCONTRADO!");
		}
		
		objetoAprendizagem.setId(id);
		objetoAprendizagem.setId(id);
		objetoAprendizagemRepository.save(objetoAprendizagem);
		return objetoAprendizagem;
	}
	
	
	public void deletar(Long id) {
		Optional<ObjetoAprendizagem> objetoAprendizagem = objetoAprendizagemRepository.findById(id);
		
		if(objetoAprendizagem.isEmpty()) {
			throw new ObjectnotFoundException("OBJETO DE APRENDIZAGEM COM ID: '\" + id + \"' NÃO ENCONTRADO!");
		}
		
		objetoAprendizagemRepository.deleteById(id);
	}
}
