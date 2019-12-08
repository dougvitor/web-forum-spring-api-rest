package br.com.home.forum.modelo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Curso {

	private Long id;
	private String nome;
	private String categoria;

	

}
