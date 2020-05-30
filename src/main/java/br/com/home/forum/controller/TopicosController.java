package br.com.home.forum.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.home.forum.dto.DetalhesDoTopicoDto;
import br.com.home.forum.dto.TopicoDto;
import br.com.home.forum.form.AtualizacaoTopicoForm;
import br.com.home.forum.form.TopicoForm;
import br.com.home.forum.mapper.service.TopicoModelMapperService;
import br.com.home.forum.modelo.Topico;
import br.com.home.forum.repository.CursoRepository;
import br.com.home.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoModelMapperService topicoMapperService;
	
	@Autowired
	private TopicoRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public Collection<TopicoDto> listarAll(){
		
		List<Topico> topicos = repository.findAll();
		
		return topicos
				.stream()
					.map(topicoMapperService::convertToDto)
						.collect(Collectors.toList());
	}
	
	@GetMapping(value = "/filtrados-por-nome-curso", produces = {MediaType.APPLICATION_JSON_VALUE})
	@Cacheable(value = "listaDeTopicos")
	public Page<TopicoDto> listarPor(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id", direction = Direction.DESC, page= 0, size = 1) Pageable paginacao){
		
		Page<Topico> topicos = null;
		
		if(StringUtils.isEmpty(nomeCurso)) {
			topicos = repository.findAll(paginacao);
		}else {
			topicos = repository.findByCurso_NomeIgnoreCaseContaining(nomeCurso, paginacao);
		}
		
		return topicos.map(topicoMapperService::convertToDto);
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico entity = topicoMapperService.convertToEntity(form);
		entity.setCurso(cursoRepository.findByNome(form.getCursoNome()));
		
		entity = repository.save(entity);
		
		URI uri = uriBuilder
					.path("/topicos/{id}")
					.buildAndExpand(entity.getId())
					.toUri();
		
		return ResponseEntity
				.created(uri)
				.body(topicoMapperService.convertToDto(entity));
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
		Optional<Topico> topico = repository.findById(id);
		
		if(topico.isPresent()) {
			return ResponseEntity.ok(topicoMapperService.convertToDetalhesDto(topico.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
		Optional<Topico> topicoBanco = repository.findById(id);
		
		if(topicoBanco.isPresent()) {
			Topico topicoAtualizado = form.atualizar(topicoBanco.get());
			return ResponseEntity.ok(topicoMapperService.convertToDto(topicoAtualizado));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Topico> topicoBanco = repository.findById(id);
		
		if(topicoBanco.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
