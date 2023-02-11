package br.com.lucaslememoura.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucaslememoura.model.dto.LivroDTO;
import br.com.lucaslememoura.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController extends BaseController<LivroDTO, LivroService> {
	
	public LivroController(LivroService service) {
		super(service);
	}

}
