package com.example.shopping.repository;

import com.example.shopping.model.Item;
import java.util.Optional;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    Optional<Item> findByName(String name);
}
