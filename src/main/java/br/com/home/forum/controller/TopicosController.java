package br.com.home.forum.controller;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.home.forum.modelo.Curso;
import br.com.home.forum.modelo.Topico;

@Controller
public class TopicosController {

	@GetMapping("/topicos")
	@ResponseBody
	public Collection<Topico> listar(){
		
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
		
		return Arrays.asList(topico);
	}
}
