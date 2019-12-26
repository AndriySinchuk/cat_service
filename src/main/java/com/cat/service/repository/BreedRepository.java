package com.cat.service.repository;

import com.cat.service.entity.CatBreed;
import org.springframework.data.repository.CrudRepository;

public interface BreedRepository extends CrudRepository<CatBreed, Long> {
}
