package ApiRestSpring.estudandorestspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Filmes criarFilmes(@RequestBody Filmes Filme) {
		return filmesRepository.save(Filme);
	}
	
	@GetMapping("/recomendacao")
	public Object filmeRecomendado() {
		List<Filmes> recomendados = filmesRepository.findByAvaliacaoIsNull();
		
		int tamanho = recomendados.size();
		
		if(tamanho == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}

		int i = (int) (Math.random() * tamanho);
		
		Object response = recomendados.get(i);
		return response;
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deletarFilme(@PathVariable Long id) {
		filmesRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	Filmes avaliarFilme(@RequestBody Filmes filmeAvaliado, @PathVariable Long id) {
		return filmesRepository.findById(id)
				.map(avaliacao -> {
					avaliacao.setAvaliacao(filmeAvaliado.getAvaliacao());
					return filmesRepository.save(avaliacao);
				})
				.orElseGet(() -> {
					filmeAvaliado.setId(id);
					return filmesRepository.save(filmeAvaliado);
				});
	}
}
