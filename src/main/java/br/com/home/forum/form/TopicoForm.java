package br.com.home.forum.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TopicoForm {
	
	@NotBlank
	@Length(min = 5)
	private String titulo;
	
	@NotBlank
	@Length(min = 10)
	private String mensagem;
	
	@NotBlank
	private String cursoNome;

}
