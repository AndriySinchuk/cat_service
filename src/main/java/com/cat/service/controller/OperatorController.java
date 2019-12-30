package com.cat.service.controller;

import com.cat.service.entity.Cat;
import com.cat.service.entity.CatBreed;
import com.cat.service.entity.CatOwner;
import com.cat.service.exception_hanling.DuplicateEntityException;
import com.cat.service.exception_hanling.ThereIsNoSuchEntityException;
import com.cat.service.repository.BreedRepository;
import com.cat.service.repository.CatRepository;
import com.cat.service.repository.OwnerRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    BreedRepository breedRepository;

    @Autowired
    CatRepository catRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation("Retrieve cat breeds")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Info retrieved")})
    public Iterable<CatBreed> readBreeds() {
        return breedRepository.findAll();
    }

    @RequestMapping(value = "/cat", method = RequestMethod.POST)
    @ApiOperation("Add cat")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cat persisted"),
            @ApiResponse(code = 403, message = "Duplicate cat not allowed")})
    public ResponseEntity addCat(@RequestBody Cat cat) throws DuplicateEntityException {
        try {
            catRepository.save(cat);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEntityException();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cat/{id}", method = RequestMethod.GET)
    @ApiOperation("Retrieve cat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Info retrieved"),
            @ApiResponse(code = 404, message = "Not found")})
    public Cat readCat(@PathVariable("id") Long id) {
        return catRepository
                .findById(id)
                .orElseThrow(ThereIsNoSuchEntityException::new);
    }

    @RequestMapping(value = "/cat/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete cat by id")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Deleted by id")})
    public ResponseEntity deleteCatById(@PathVariable("id") Long id) {
        catRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/owner", method = RequestMethod.POST)
    @ApiOperation("Add owner")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Owner persisted")})
    public Long addCatOwner(@RequestBody CatOwner catOwner) {

        return ownerRepository
                .save(catOwner)
                .getOwner_id();
    }

    @RequestMapping(value = "/owner"
            ,params = {"name","ownerSecondName"}
            ,method = RequestMethod.GET)
    @ApiOperation("Retrieve owner by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieved owner by name")})
    public CatOwner findCatOwnerByOwnerNameAndOwnerSecondName(@RequestParam String name,@RequestParam String ownerSecondName) {

        return ownerRepository.findCatOwnerByOwnerNameAndOwnerSecondName(name, ownerSecondName);
    }

    @RequestMapping(value = "owner/delete{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete cat owner")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Owner deleted")})
    public ResponseEntity deleteCatOwnerById(@PathVariable("id") Long id) {
        ownerRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/owner/{id}", method = RequestMethod.GET)
    @ApiOperation("Retrieve cat owner")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Info retrieved"),
            @ApiResponse(code = 404, message = "Not found")})
    public CatOwner readOwner(@PathVariable("id") Long id) {
        return ownerRepository
                .findById(id)
                .orElseThrow(ThereIsNoSuchEntityException::new);
    }
}
