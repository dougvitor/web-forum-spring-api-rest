package br.com.home.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.home.forum.config.security.service.TokenService;
import br.com.home.forum.dto.TokenDto;
import br.com.home.forum.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
		try {
			Authentication authentication = authManager.authenticate(form.convertToAuth());
			
			String token = tokenService.generateToken(authentication);
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		}catch(AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
