package br.apicombustivelpublico.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "grupo")
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private Categoria categoria;
	
	public Grupo (Categoria categoria) {
		this.categoria = categoria;
	}

	public Grupo () {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	

	
	
	
	
}
