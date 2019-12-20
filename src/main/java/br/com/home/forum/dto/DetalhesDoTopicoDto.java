package br.com.home.forum.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.home.forum.modelo.StatusTopico;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DetalhesDoTopicoDto {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String cursoNome;
	private String cursoCategoria;
	private String autorNome;
	private StatusTopico status;
	private List<RespostaDto> respostas;

}
