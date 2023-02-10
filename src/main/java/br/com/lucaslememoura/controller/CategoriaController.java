package br.com.lucaslememoura.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucaslememoura.model.dto.CategoriaDTO;
import br.com.lucaslememoura.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController extends BaseController<CategoriaDTO, CategoriaService> {
	
	public CategoriaController(CategoriaService service) {
		
	}

}
