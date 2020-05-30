package br.com.home.forum.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

	@Email
	private String principal;
	
	@NotBlank
	private String credentials;

	public Authentication convertToAuth() {
		return new UsernamePasswordAuthenticationToken(this.principal, this.credentials) ;
	}
}
