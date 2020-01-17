package com.cat.service.repository;

import com.cat.service.entity.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long>, PetRepositoryCustom {
}
