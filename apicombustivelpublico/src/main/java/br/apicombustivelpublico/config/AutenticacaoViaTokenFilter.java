package br.apicombustivelpublico.config;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.apicombustivelpublico.model.Usuario;
import br.apicombustivelpublico.repository.IUsuarioRepository;


public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	
	private String tipoAutenticacao = "Bearer ";
	private String KeyRequest = "Authorization";
	private ServicoToken tokenService;
	private IUsuarioRepository repository;
	
	public AutenticacaoViaTokenFilter() {}

	public AutenticacaoViaTokenFilter(ServicoToken tokenService, IUsuarioRepository repositorioUsuario) {
		this.tokenService = tokenService;
		this.repository = repositorioUsuario;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		 String jwt = recuperarToken(request);
	      if (jwt != null && tokenService.isTokenValido(jwt)) {
	     
	    	String idusuario = tokenService.getUsuarioJWTToekn(jwt);
			Usuario usuario = repository.findById(idusuario).get();
	        
	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
	        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	      }
	}

	private String recuperarToken(HttpServletRequest request) {
		
		String token = request.getHeader(KeyRequest);
		if(token == null || token.isEmpty() || !token.startsWith(tipoAutenticacao)) {
			return null;
		}
		return token.substring(7,token.length());
	}


	
	
}
