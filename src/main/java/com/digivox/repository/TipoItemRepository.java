package com.digivox.repository;

import org.springframework.data.repository.CrudRepository;

import com.digivox.models.TipoItem;

public interface TipoItemRepository extends CrudRepository<TipoItem, Long> {
	TipoItem findById(long id);
}
