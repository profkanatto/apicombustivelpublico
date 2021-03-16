package br.apicombustivelpublico.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.apicombustivelpublico.model.Cliente;

public class ClienteForm {
	
	
	private String id;
	
	@NotBlank
	@Size(min = 4, max = 20)
	private String nome;
	
	@NotBlank
	@Size(min = 10, max = 50)
	@Email
	private String email;
	
	
	private List<ClienteForm> clientesForm;

	public  ClienteForm() {}
	
	public  ClienteForm(Cliente cliente){
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}
	
	public List<ClienteForm> converter(List<Cliente> cliente){
		
		clientesForm = new ArrayList<ClienteForm>();
		cliente.forEach(cli -> { 
			clientesForm.add(new ClienteForm(cli)); 
		});
		
		return clientesForm;
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
	
	
	
	
	

}
