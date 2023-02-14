package br.com.lucaslememoura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucaslememoura.model.dto.LivroDTO;
import br.com.lucaslememoura.service.LivroService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/livros")
public class LivroController extends BaseController<LivroDTO, LivroService> {
	
	
	@Autowired
	private LivroService service;
	
	public LivroController(LivroService service) {
		super(service);
	}


	@PostMapping("/filtrar")
    public ResponseEntity<List<LivroDTO>> filter(@RequestBody LivroDTO livroDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(service.filter(livroDTO));

        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
