package br.com.lucaslememoura.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO{
	
		private Long id;
		private String nome;
		private String email;
		private Long perfil;
}
