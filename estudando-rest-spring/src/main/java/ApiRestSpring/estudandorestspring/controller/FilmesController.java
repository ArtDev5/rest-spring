package ApiRestSpring.estudandorestspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ApiRestSpring.estudandorestspring.model.Filmes;
import ApiRestSpring.estudandorestspring.repository.FilmesRepository;

@RestController
@RequestMapping("/filmes")
public class FilmesController {
	
	@Autowired
	private FilmesRepository filmesRepository;
	
	@GetMapping
	public List<Filmes> listar(){
		return filmesRepository.findAll();
	}
}
