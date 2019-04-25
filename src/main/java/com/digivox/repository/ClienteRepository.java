package com.digivox.repository;

import org.springframework.data.repository.CrudRepository;

import com.digivox.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	Cliente findById(long id);
}
