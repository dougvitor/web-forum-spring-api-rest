package br.com.home.forum.modelo;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Topico {
	
	private Long id;
	private String titulo;
	private String mensagem;
	@Default
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@Default
	private StatusTopico status = StatusTopico.NAO_RESPONDIDO;
	private Usuario autor;
	private Curso curso;
	@Singular
	private List<Resposta> respostas;

}
