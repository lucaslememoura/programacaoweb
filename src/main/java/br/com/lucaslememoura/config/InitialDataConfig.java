package br.com.lucaslememoura.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.lucaslememoura.model.entity.Perfil;
import br.com.lucaslememoura.repository.PerfilRepository;

@Configuration
public class InitialDataConfig {

	@Autowired
	private PerfilRepository repository;
	
	@Bean
	public void inserirDados() {
		Perfil perfilUm = Perfil.builder().nome("ROLE_ADMIN").descricao("Perfil de administrador").build();
		Perfil perfilDois = Perfil.builder().nome("ROLE_USER").descricao("Perfil de usuário comum").build();
		
		repository.save(perfilUm);
		repository.save(perfilDois);
	}
}
