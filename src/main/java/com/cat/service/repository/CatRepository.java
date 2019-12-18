package com.cat.service.repository;

import com.cat.service.entity.Cat;
import org.springframework.data.repository.CrudRepository;

public interface CatRepository extends CrudRepository<Cat, Long> {

}
