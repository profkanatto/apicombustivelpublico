package br.apicombustivelpublico.form;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioForm  {

	@NotBlank
	@Size(min = 4, max = 20)
	private String nome;
	
	@NotBlank
	@Size(min = 10, max = 50)
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 4, max = 20)
	private String senha;
		
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Set<String> grupos;


	
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

	public Set<String> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<String> grupos) {
		this.grupos = grupos;
	}
	
	
	
}
