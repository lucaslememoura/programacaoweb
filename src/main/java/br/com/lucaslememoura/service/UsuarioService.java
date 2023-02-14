package br.com.lucaslememoura.service;

import br.com.lucaslememoura.model.dto.TokenDTO;
import br.com.lucaslememoura.model.dto.UsuarioLoginDTO;

public interface UsuarioService extends BaseService<UsuarioLoginDTO>{
	TokenDTO logar(UsuarioLoginDTO usuarioLoginDTO) throws Exception;
	TokenDTO atualizarToken(String refreshToken);
}
