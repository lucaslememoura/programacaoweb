package br.com.lucaslememoura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucaslememoura.model.dto.TokenDTO;
import br.com.lucaslememoura.model.dto.UsuarioLoginDTO;
import br.com.lucaslememoura.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController extends BaseController<UsuarioLoginDTO, UsuarioService>  {
	@Autowired
	UsuarioService service;
	

	public UsuarioController(UsuarioService service) {
		super(service);
	}
	
	@PostMapping("/auth")
	public ResponseEntity<TokenDTO> logar(@RequestBody @Valid UsuarioLoginDTO entidade) {
		try {

            return ResponseEntity.ok(service.logar(entidade));

        }catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
	}
	
	@GetMapping("/auth/{refreshToken}")
	public ResponseEntity<TokenDTO> atualizarToken(@PathVariable("refreshToken") String refreshToken) {
		try {

            return ResponseEntity.ok(service.atualizarToken(refreshToken));

        }catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
	}
}
