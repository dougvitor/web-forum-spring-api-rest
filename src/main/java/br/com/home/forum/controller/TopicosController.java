package br.com.home.forum.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.home.forum.dto.TopicoDto;
import br.com.home.forum.modelo.Curso;
import br.com.home.forum.modelo.Topico;
import br.com.home.forum.service.ModelMapperService;

@RestController
public class TopicosController {
	
	@Autowired
	private ModelMapperService modelMapperService;

	@GetMapping("/topicos")
	public Collection<TopicoDto> listar(){
		
		Topico topico = Topico
							.builder()
								.titulo("Dúvida")
								.mensagem("Dúvida com Spring")
								.curso(Curso
										.builder()
											.nome("Spring")
											.categoria("Programação")
										.build())
							.build();
		
		List<Topico> topicos = new ArrayList<Topico>();
		topicos.add(topico);
		
		return topicos
				.stream()
					.map(modelMapperService::convertToDto)
						.collect(Collectors.toList());
	}
	
}
