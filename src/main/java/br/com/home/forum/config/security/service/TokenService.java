package br.com.home.forum.config.security.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.home.forum.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		Usuario user = (Usuario) authentication.getPrincipal();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime oneDayExpiration = now.plusSeconds(Long.parseLong(this.expiration));
		
		return Jwts.builder()
				.setIssuer("API-Forum")
				.setSubject(user.getId().toString())
				.setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
				.setExpiration(Date.from(oneDayExpiration.atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}
}
