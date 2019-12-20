package br.com.home.forum.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.home.forum.modelo.Topico;
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
public class AtualizacaoTopicoForm {
	
	@NotBlank
	@Length(min = 5)
	private String titulo;
	
	@NotBlank
	@Length(min = 10)
	private String mensagem;

	public Topico atualizar(Topico topicoBanco) {
		topicoBanco.setTitulo(this.titulo);
		topicoBanco.setMensagem(this.mensagem);
		return topicoBanco;
	}

}
