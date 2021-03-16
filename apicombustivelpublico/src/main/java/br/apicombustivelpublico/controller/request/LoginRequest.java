package br.apicombustivelpublico.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
    @NotBlank(message = "Email não poder ser vazio!")
    @Email(message = "e-mail Inválido!")
    private String email;

    @NotBlank(message = "Senha não pode estar vazia!")
    private String senha;

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


}
