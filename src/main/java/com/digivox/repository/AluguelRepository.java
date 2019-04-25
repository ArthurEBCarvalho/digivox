package com.digivox.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.digivox.models.Aluguel;

public interface AluguelRepository extends CrudRepository<Aluguel, Long> {
	Aluguel findById(long id);
	
	Iterable<Aluguel> findByTipo(String tipo);
	
	@Query("SELECT a FROM Aluguel a where a.inicio between :inicio and :fim") 
	Iterable<Aluguel> findByInicio(Date inicio, Date fim);
	
	@Query("SELECT a FROM Aluguel a where a.fim between :inicio and :fim") 
	Iterable<Aluguel> findByFim(Date inicio, Date fim);
	
	@Query("SELECT a FROM Aluguel a where a.item.id = :idItem and ((a.inicio < :inicio and a.fim > :fim) or (a.inicio between :inicio and :fim) or (a.fim between :inicio and :fim))")
	Iterable<Aluguel> findItemByPeriodo(long idItem, Date inicio, Date fim);
}
