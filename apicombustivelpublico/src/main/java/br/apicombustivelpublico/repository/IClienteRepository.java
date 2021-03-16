package br.apicombustivelpublico.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.apicombustivelpublico.model.Cliente;
import br.apicombustivelpublico.model.Usuario;

public interface IClienteRepository extends MongoRepository<Cliente, String> {

	 Boolean existsByEmail(String email);
	 
	 public void save(Usuario usuario);
	
}
