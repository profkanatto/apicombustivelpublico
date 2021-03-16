package br.apicombustivelpublico.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.apicombustivelpublico.config.ServicoToken;
import br.apicombustivelpublico.controller.response.JwtResponse;
import br.apicombustivelpublico.form.LoginForm;
import br.apicombustivelpublico.model.Usuario;


@RestController
@RequestMapping("/")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ServicoToken tokenService;


	@CrossOrigin(maxAge = 3600)
	@PostMapping("login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm) {

		Authentication authentication;
		
		try {
				authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getSenha()));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt = tokenService.gerarToken(authentication);
				
				Usuario usuario = (Usuario) authentication.getPrincipal();		
				List<String> grupo = usuario.getGrupo().stream()
						.map(item -> item.getCategoria().name())
						.collect(Collectors.toList());
			
				return ResponseEntity.ok(new JwtResponse(jwt, 
														 usuario.getId(), 
														 usuario.getNome(), 
														 usuario.getEmail(), 
														 grupo));	
				
				
		} catch (Exception e) {
			return new ResponseEntity<>("Problemas no login: Usuário não encontrado!", HttpStatus.BAD_REQUEST);
		}

	}
	

	
}
