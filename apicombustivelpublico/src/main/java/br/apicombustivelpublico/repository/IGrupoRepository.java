package br.apicombustivelpublico.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.apicombustivelpublico.model.Categoria;
import br.apicombustivelpublico.model.Grupo;

public interface IGrupoRepository extends MongoRepository<Grupo, String>{
	
	Optional<Grupo> findByCategoria(Categoria categoria);

}
