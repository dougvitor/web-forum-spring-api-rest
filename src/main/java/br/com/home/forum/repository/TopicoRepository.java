package br.com.home.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	List<Topico> findByCurso_NomeIgnoreCaseContaining(String nomeCurso);

}
