package br.apicombustivelpublico.controller.request;

import java.util.Set;

public class CadastroClienteRequest {
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	private Set<String> grupo;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<String> getGrupo() {
		return grupo;
	}

	public void setGrupo(Set<String> grupo) {
		this.grupo = grupo;
	}
	 
	 
	 
}
