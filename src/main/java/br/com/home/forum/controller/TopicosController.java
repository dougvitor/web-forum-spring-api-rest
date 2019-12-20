package br.com.home.forum.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.home.forum.dto.TopicoDto;
import br.com.home.forum.form.TopicoForm;
import br.com.home.forum.modelo.Topico;
import br.com.home.forum.repository.CursoRepository;
import br.com.home.forum.repository.TopicoRepository;
import br.com.home.forum.service.ModelMapperService;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private ModelMapperService modelMapperService;
	
	@Autowired
	private TopicoRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public Collection<TopicoDto> listarAll(){
		
		List<Topico> topicos = repository.findAll();
		
		return topicos
				.stream()
					.map(modelMapperService::convertToDto)
						.collect(Collectors.toList());
	}
	
	@GetMapping("/filtrados-por-nome-curso")
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
	
	@PostMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico entity = modelMapperService.convertToEntity(form);
		entity.setCurso(cursoRepository.findByNome(form.getCursoNome()));
		
		entity = repository.save(entity);
		
		URI uri = uriBuilder
					.path("/topicos/{id}")
					.buildAndExpand(entity.getId())
					.toUri();
		
		return ResponseEntity
				.created(uri)
				.body(modelMapperService.convertToDto(entity));
	}
	
}
