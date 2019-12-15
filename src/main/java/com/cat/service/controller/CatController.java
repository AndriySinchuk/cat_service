package com.cat.service.controller;

import com.cat.service.entity.Cat;
import com.cat.service.service.interfaces.CatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/cat")
public class CatController {
    @Autowired
    private CatRepo catRepo;

    @PostMapping("addCat")
    public ResponseEntity<Void> addCat(@RequestBody Cat cat, UriComponentsBuilder builder) {
        boolean bool = catRepo.addCat(cat);
        if (bool == false) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/cat/{id}").buildAndExpand(cat.getCatId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

    }
}
