package com.cat.service.controller;

import com.cat.service.entity.Cat;
import com.cat.service.exception_hanling.ThereIsNoSuchEntityException;
import com.cat.service.repository.CatRepository;
import com.google.common.collect.ImmutableList;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @RequestMapping(value = "/cat", method = RequestMethod.POST)
    @ApiOperation("Add cat")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cat persisted")})
    public ResponseEntity addCat(@RequestBody Cat cat) {
        catRepository.save(cat);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cat/{id}", method = RequestMethod.POST)
    @ApiOperation("Retrieve cat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cat info retrieved"),
            @ApiResponse(code = 404, message = "Cat not found")})
    public ResponseEntity readCat(@PathVariable("id") Long id) {
        Optional<Cat> retrievedCat = catRepository.findById(id);
        List<Cat> searchResult = new ArrayList<>();
        retrievedCat.ifPresent(searchResult::add);
        if (searchResult.size() < 1) {
            throw new ThereIsNoSuchEntityException();
        } else
            return new ResponseEntity<>(ImmutableList.of(searchResult), HttpStatus.OK);
    }

    @RequestMapping(value = "/cat/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete cat by id")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Cat deleted by id")})
    public ResponseEntity deleteCatById(@PathVariable("id") Long id) {
        catRepository.deleteById(id);
        return new ResponseEntity<>("Cat with id #" + id + " deleted", HttpStatus.ACCEPTED);
    }
}
