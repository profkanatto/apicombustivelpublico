package br.apicombustivelpublico.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.apicombustivelpublico.controller.response.MensagemResponse;
import br.apicombustivelpublico.form.ClienteForm;
import br.apicombustivelpublico.form.UsuarioForm;
import br.apicombustivelpublico.model.Categoria;
import br.apicombustivelpublico.model.Cliente;
import br.apicombustivelpublico.model.Grupo;
import br.apicombustivelpublico.model.Usuario;
import br.apicombustivelpublico.repository.IClienteRepository;
import br.apicombustivelpublico.repository.IGrupoRepository;
import br.apicombustivelpublico.repository.IUsuarioRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/combustivel")
public class CombustivelController {

	@Autowired
	private IClienteRepository repositorioCliente;
	
	@Autowired
	private IUsuarioRepository repositorioUsuario;

	@Autowired
	private IGrupoRepository repositorioGrupo;
	
	@Autowired
	private PasswordEncoder encoder;


	

	@GetMapping("/clientes")
	public List<ClienteForm> listarTodosClientes() {
		
		List<Cliente> clientes = repositorioCliente.findAll();
		
		return new ClienteForm().converter(clientes);
	}
	

	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody UsuarioForm usuarioForm) {
	
	if ( repositorioCliente.existsByEmail(usuarioForm.getEmail())) {
		return ResponseEntity.badRequest().body("Erro: Usuário já tem um registro");
	}

	Usuario usuario = new Usuario(usuarioForm.getNome(), usuarioForm.getEmail(), encoder.encode(usuarioForm.getSenha()));
	Set<String> strGrupos = usuarioForm.getGrupos();
	
	Cliente cliente = new Cliente(usuarioForm.getNome(), usuarioForm.getEmail());
	
	
	Set<Grupo> grupos = new HashSet<>();
	
	if (strGrupos == null) {
		Grupo usuarioGrupo = repositorioGrupo.findByCategoria(Categoria.USUARIO)
				.orElseThrow(() -> new RuntimeException("Erro: Grupo não Encontrado."));
		grupos.add(usuarioGrupo);
	} else {
		strGrupos.forEach(grupo -> {
			switch (grupo) {
			case ("ADMIN"):
				Grupo adminGrupo = repositorioGrupo.findByCategoria(Categoria.ADMIN)
						.orElseThrow(() -> new RuntimeException("Erro: Grupo não Encontrado."));
				grupos.add(adminGrupo);
			}
			// Código pronto para mais permissões
		});
	}

	usuario.setGrupo(grupos);
	repositorioCliente.save(cliente);
	repositorioUsuario.save(usuario);
	
	return ResponseEntity.ok(new MensagemResponse("Usuário cadastrado com sucesso!"));

}

}
