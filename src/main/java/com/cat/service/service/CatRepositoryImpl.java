package com.cat.service.service;

import com.cat.service.entity.Cat;
import com.cat.service.repository.CatRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository("CatRepo")
@Transactional
public class CatRepositoryImpl implements CatRepository {

    @Override
    public <S extends Cat> S save(S s) {
        return s;
    }

    @Override
    public <S extends Cat> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Cat> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Cat> findAll() {
        return null;
    }

    @Override
    public Iterable<Cat> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Cat cat) {

    }

    @Override
    public void deleteAll(Iterable<? extends Cat> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
