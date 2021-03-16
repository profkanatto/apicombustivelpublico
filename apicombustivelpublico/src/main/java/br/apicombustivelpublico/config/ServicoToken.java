package br.apicombustivelpublico.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.apicombustivelpublico.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class ServicoToken {
	
	@Value("${jwt.expiration}")
	private String expiration;
	
	@Value("${jwt.secret}")
	private String secret;
	

	public String gerarToken(Authentication authentication) {
	
		Usuario logado = (Usuario) authentication.getPrincipal();		
	
		return Jwts.builder()
				.setIssuer("API teste local")
				.setSubject(logado.getId().toString())
				.claim("email", logado.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	
	public boolean isTokenValido(String token) {
		
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUsuarioJWTToekn(String token) {
		return Jwts.parser().setSigningKey(token).parseClaimsJws(token).getBody().getSubject();
	}
	

}