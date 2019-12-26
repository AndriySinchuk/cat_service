package com.cat.service.controller;

import com.cat.service.entity.CatBreed;
import com.cat.service.exception_hanling.ThereIsNoSuchEntityException;
import com.cat.service.repository.BreedRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/breed")
public class CatBreedController {
    @Autowired
    BreedRepository breedRepository;

    Logger logger;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation("Add cat breed")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Breed persisted")})
    public ResponseEntity addCat(@RequestBody CatBreed breed) {
        try {
            breedRepository.save(breed);
        } catch (HibernateException ex) {
            logger.error("", ex);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Retrieve cat breed")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Info retrieved"),
            @ApiResponse(code = 404, message = "Not found")})
    public CatBreed readBreed(@PathVariable("id") Long id) {
        return breedRepository
                .findById(id)
                .orElseThrow(ThereIsNoSuchEntityException::new);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete breed by id")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Deleted by id")})
    public ResponseEntity deleteCatById(@PathVariable("id") Long id) {
        breedRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
