package ApiRestSpring.estudandorestspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ApiRestSpring.estudandorestspring.model.Filmes;

@RestController
@RequestMapping("/filmes")
public class FilmesController {
	
	public List<Filmes> listar(){
		
	}
}
