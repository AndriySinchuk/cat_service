package com.cat.service.repository;

import com.cat.service.entity.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PetRepositoryCustom extends CrudRepository<Pet, Long> {

    List<Pet> findPetsByDiagnosis(String diagnosis);

    List<Pet> findPetsByDiagnosisAndPassedInGreaterThanEqualAndPassedOutLessThanEqual(String diagnosis, Date dateIn, Date dateOut);

    List<Pet> findPetsByCustomerId(Long customerId);
}