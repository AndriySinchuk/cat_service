package com.cat.service.controller;

import com.cat.service.entity.Pet;
import com.cat.service.exception_hanling.DuplicateEntityException;
import com.cat.service.exception_hanling.ThereIsNoSuchEntityException;
import com.cat.service.repository.BreedRepository;
import com.cat.service.repository.PetRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    BreedRepository breedRepository;

    @Autowired
    PetRepository petRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("Add pet or update by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet persisted"),
            @ApiResponse(code = 403, message = "Duplicate pet not allowed"),
            @ApiResponse(code = 404, message = "Pet not found")})
    public ResponseEntity addCat(@RequestBody Pet pet) throws DuplicateEntityException {
        try {
            petRepository.save(pet);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEntityException();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Retrieve pet by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Info retrieved"),
            @ApiResponse(code = 404, message = "Not found")})
    public Pet readCat(@PathVariable("id") Long id) {
        return petRepository
                .findById(id)
                .orElseThrow(ThereIsNoSuchEntityException::new);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete pet by id")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Deleted by id")})
    public ResponseEntity deleteCatById(@PathVariable("id") Long id) {
        petRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/by_diagnosis/{diagnosis}", method = RequestMethod.GET)
    @ApiOperation("Find pets by diagnosis")
    public Iterable<Pet> findPetsByDiagnosis(@RequestParam String diagnosis) {

        return petRepository.findPetsByDiagnosis(diagnosis);
    }

    @RequestMapping(value = "/by_date{date}", method = RequestMethod.GET)
    @ApiOperation("Find pets by date")
    public Iterable<Pet> findPetsByDate(@RequestParam String date ) throws ParseException {
        return petRepository.findPetByCreatedAt(new SimpleDateFormat("yyyy-MM-dd").parse(date));
    }

}
