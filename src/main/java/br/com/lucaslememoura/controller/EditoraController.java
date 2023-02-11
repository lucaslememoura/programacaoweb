package br.com.lucaslememoura.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucaslememoura.model.dto.EditoraDTO;
import br.com.lucaslememoura.service.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraController extends BaseController<EditoraDTO, EditoraService>  {
	
	public EditoraController(EditoraService service) {
		super(service);
	}

}
