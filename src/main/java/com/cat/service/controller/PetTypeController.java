package com.cat.service.controller;

import com.cat.service.entity.PetType;
import com.cat.service.exception_hanling.DuplicateEntityException;
import com.cat.service.repository.BreedRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/breed")
public class PetTypeController {

    @Autowired
    BreedRepository breedRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("Add breed or update by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Breed persisted"),
            @ApiResponse(code = 403, message = "Duplicate pet not allowed")})
    public ResponseEntity addPetBreed(@RequestBody PetType breed) throws DuplicateEntityException {
        try {
            breedRepository.save(breed).getId().intValue();
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEntityException();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
    @ApiOperation("Retrieve all pet breeds")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Info retrieved")})
    public Iterable<PetType> readBreeds() {
        return breedRepository.findAll();
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
