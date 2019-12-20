package br.com.home.forum.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.home.forum.dto.TopicoDto;
import br.com.home.forum.modelo.Topico;
import br.com.home.forum.repository.TopicoRepository;
import br.com.home.forum.service.ModelMapperService;

@RestController
public class TopicosController {
	
	@Autowired
	private ModelMapperService modelMapperService;
	
	@Autowired
	private TopicoRepository repository;

	@GetMapping("/topicos")
	public Collection<TopicoDto> listarAll(){
		
		List<Topico> topicos = repository.findAll();
		
		return topicos
				.stream()
					.map(modelMapperService::convertToDto)
						.collect(Collectors.toList());
	}
	
	@GetMapping("/topicos-filtrados-por-curso")
	public Collection<TopicoDto> listarPor(String nomeCurso){
		
		List<Topico> topicos = null;
		
		if(StringUtils.isEmpty(nomeCurso)) {
			topicos = repository.findAll();
		}else {
			topicos = repository.findByCurso_NomeIgnoreCaseContaining(nomeCurso);
		}
		
		return topicos
				.stream()
					.map(modelMapperService::convertToDto)
						.collect(Collectors.toList());
	}
	
}
