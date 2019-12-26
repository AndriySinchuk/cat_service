package com.cat.service.repository;

import com.cat.service.entity.CatOwner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<CatOwner, Long> {
}
