package com.cat.service.repository;

import com.cat.service.entity.CatOwner;

public interface OwnerRepositoryCustom {

    CatOwner findCatOwnerByOwnerNameAndOwnerSecondName(String name, String ownerName);
}
