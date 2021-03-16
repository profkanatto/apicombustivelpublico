package br.apicombustivelpublico.controller.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String tipo = "Bearer";
	private String id;
	private String nome;
	private String email;
	private List<String> grupo;

	public JwtResponse(String accessToken, String id, String username, String email, List<String> grupo) {
		this.token = accessToken;
		this.id = id;
		this.nome = username;
		this.email = email;
		this.grupo = grupo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<String> grupo) {
		this.grupo = grupo;
	}

	
}
