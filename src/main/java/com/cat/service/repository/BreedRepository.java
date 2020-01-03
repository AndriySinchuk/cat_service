package com.cat.service.repository;

import com.cat.service.entity.PetType;
import org.springframework.data.repository.CrudRepository;

public interface BreedRepository extends CrudRepository<PetType, Long> {
}
