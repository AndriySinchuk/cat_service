package com.cat.service.repository;

import com.cat.service.entity.Cat;
import com.google.common.collect.ImmutableList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatRepository extends CrudRepository<Cat, Long> {

    List <Cat> findCatByCatName(final String catName);

//    ImmutableList<Cat> findCatByOwnerId(Integer ownerId);

//    ImmutableList<Cat> findByCatNameAndOwnerId(String catName, Integer OwnerId);

}
