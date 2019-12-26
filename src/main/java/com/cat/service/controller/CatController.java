package com.cat.service.controller;

import com.cat.service.entity.Cat;
import com.cat.service.exception_hanling.DuplicateEntityException;
import com.cat.service.exception_hanling.ThereIsNoSuchEntityException;
import com.cat.service.repository.CatRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @RequestMapping(value = "/cat", method = RequestMethod.POST)
    @ApiOperation("Add cat")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cat persisted"),
            @ApiResponse(code = 403, message = "Slave can't serve multiple cats with one name!!!")})
    public ResponseEntity addCat(@RequestBody Cat cat) throws DuplicateEntityException {
        try {
            catRepository.save(cat);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEntityException();
        }
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
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

    @RequestMapping(value = "/cat/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete cat by id")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Deleted by id")})
    public ResponseEntity deleteCatById(@PathVariable("id") Long id) {
        catRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/write/{id}", method = RequestMethod.GET)
    public void saveCatToDisk(@PathVariable("id") Long id) throws IOException {
        File file = new File("C:\\Users\\asinchuk\\Documents\\cats\\cat_" + id + ".txt");
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        Optional<Cat> retrievedCat = catRepository.findById(id);
        List searchResult = Collections.singletonList(retrievedCat.get());
            try {
                output.write(searchResult.toString());
            } finally {
                output.close();
            }
    }
}
