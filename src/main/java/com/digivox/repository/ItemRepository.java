package com.digivox.repository;

import org.springframework.data.repository.CrudRepository;

import com.digivox.models.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
	Item findById(long id);
}
