package com.cat.service.service;

import com.cat.service.entity.Cat;
import com.cat.service.repository.CatRepository;
import com.cat.service.service.interfaces.CatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository("CatRepo")
public class CatRepoImpl implements CatRepo {
    @Autowired
    CatRepository catRepository;

    @Override
    public boolean addCat(Cat cat) {
        List<com.cat.service.entity.Cat> catsList = catRepository.findCatByCatName(cat.getCatName());
        if (catsList.size() > 0) {
            return false;
        } else {
            catRepository.save(cat);
            return true;
        }
    }
}
