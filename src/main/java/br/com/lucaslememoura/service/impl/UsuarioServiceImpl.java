package br.com.lucaslememoura.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lucaslememoura.model.dto.TokenDTO;
import br.com.lucaslememoura.model.dto.UsuarioDTO;
import br.com.lucaslememoura.model.dto.UsuarioLoginDTO;
import br.com.lucaslememoura.model.entity.Usuario;
import br.com.lucaslememoura.model.mapper.UsuarioMapper;
import br.com.lucaslememoura.repository.UsuarioRepository;
import br.com.lucaslememoura.service.UsuarioService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public List<UsuarioLoginDTO> findAll() {
		return mapper.parseListDTO(repository.findAll());
	}
	
	public UsuarioLoginDTO findById(Long id) {
		Optional<Usuario> usuarioOp = repository.findById(id);
		if(usuarioOp.isPresent()) {
			Usuario usuario = usuarioOp.get();
			return mapper.parseDTO(usuario);
		}
		
		throw new EntityNotFoundException();
	}
	
	public UsuarioLoginDTO create(UsuarioLoginDTO clienteDTO) {
		Usuario usuario = mapper.parseEntity(clienteDTO);
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuario.setId(null);
		repository.save(usuario);
		return mapper.parseDTO(usuario);
	}
	
	public UsuarioLoginDTO edit(Long id, UsuarioLoginDTO usuarioDTO) {
		
		Optional<Usuario> usuarioOp = repository.findById(id);
		
		if(usuarioOp.isPresent()) {
			Usuario usuario = usuarioOp.get();
			usuario.setNome(usuarioDTO.getNome());
			usuario.setEmail(usuarioDTO.getEmail());
			usuario.setId(id);
			usuario = repository.save(usuario);
			return mapper.parseDTO(usuario);
		}
		
		throw new EntityNotFoundException();
	}
	
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new EntityNotFoundException();
		}
		
		repository.deleteById(id);
	}
	
	public TokenDTO atualizarToken(String refreshToken) {
		
		if(jwtService.validRefreshToken(refreshToken)) {
			String username = jwtService.getUsernameByRefreshToken(refreshToken);
			
			return buildTokenDTO(username,null);
		}
		
		throw new ExpiredJwtException(null, null,"Refresh token foi expirado.");
	}
	
	public TokenDTO logar(UsuarioLoginDTO usuarioLoginDTO) throws AuthenticationException,UsernameNotFoundException {
		
		UsernamePasswordAuthenticationToken autentication = 
				new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getUsername(),usuarioLoginDTO.getPassword());
		
		authenticationManager.authenticate(autentication);
		
		Usuario usuario = (Usuario) authService.loadUserByUsername(usuarioLoginDTO.getUsername());
		
		return buildTokenDTO(usuario.getUsername(),usuario);
	}
	
	private TokenDTO buildTokenDTO(String username,Usuario usuario) {
		
		UsuarioDTO usuarioDTO = null;
		if(usuario!=null) {
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setId(usuario.getId());
			usuarioDTO.setNome(usuario.getNome());
			usuarioDTO.setEmail(usuario.getEmail());
			usuarioDTO.setPerfil(usuario.getPerfil().getId());
		}
		
		String token = jwtService.generateToken(username);
		String refreshToken = jwtService.generateRefreshToken(username);
		return TokenDTO.builder()
				.token(token)
				.refreshToken(refreshToken)
				.type("Bearer")
				.user(usuarioDTO)
				.build();
	}
}
