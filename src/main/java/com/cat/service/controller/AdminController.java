package com.cat.service.controller;

import com.cat.service.entity.CatBreed;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    BreedRepository breedRepository;

    Logger logger;

    @RequestMapping(value = "/breed", method = RequestMethod.POST)
    @ApiOperation("Add cat breed")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Breed persisted")})
    public ResponseEntity addCatBreed(@RequestBody CatBreed breed) {
        try {
            breedRepository.save(breed).getId();
        } catch (HibernateException ex) {
            logger.error("", ex);
        } return new ResponseEntity(HttpStatus.CONFLICT);
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
