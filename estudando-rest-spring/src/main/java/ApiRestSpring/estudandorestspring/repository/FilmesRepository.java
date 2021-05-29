package ApiRestSpring.estudandorestspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ApiRestSpring.estudandorestspring.model.Filmes;

@Repository
public interface FilmesRepository extends JpaRepository<Filmes, Long>{

	List<Filmes> findByAvaliacaoIsNull();
}
