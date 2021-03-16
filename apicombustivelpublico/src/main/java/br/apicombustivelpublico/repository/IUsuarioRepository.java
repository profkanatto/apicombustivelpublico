package br.apicombustivelpublico.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.apicombustivelpublico.model.Usuario;


public interface IUsuarioRepository extends MongoRepository<Usuario, String> {
	
	Optional<Usuario> findByEmail(String email);

	Boolean existsByEmail(String email);
	
	
}
 