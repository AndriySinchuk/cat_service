package com.cat.service.controller;

import com.cat.service.entity.Cat;
import com.cat.service.repository.CatRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @RequestMapping(value = "/create_cat", method = RequestMethod.POST)
    @ApiOperation("Add cat")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cat persisted")})
    public ResponseEntity addCat(@RequestBody Cat cat) {
        URI location = URI.create("/create_cat");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        catRepository.save(cat);
        return new ResponseEntity<>(cat, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/retrieve_cat{id}", method = RequestMethod.POST)
    @ApiOperation("Retrieve cat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cat info retrieved")})
    public ResponseEntity readCat(@PathVariable("id") Long id) {
        URI location = URI.create("/retrieve_cat");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        Optional<Cat> retrievedCat =  catRepository.findById(id);
        return new ResponseEntity<>(retrievedCat, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete cat by id")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Cat deleted by id")})
    public ResponseEntity deleteCatById(@PathVariable("id") Long id) {
        URI location = URI.create("/delete");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        catRepository.deleteById(id);
        return new ResponseEntity<>("Cat with id #" + id + " deleted", responseHeaders, HttpStatus.ACCEPTED);
    }
}
