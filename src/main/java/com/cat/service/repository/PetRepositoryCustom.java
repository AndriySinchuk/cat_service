package com.cat.service.repository;

import com.cat.service.entity.Pet;

import java.util.Date;

public interface PetRepositoryCustom {
    Iterable<Pet> findPetsByDiagnosis(String diagnosis);
    Iterable<Pet> findPetByCreatedAt(Date createdAt);
}
