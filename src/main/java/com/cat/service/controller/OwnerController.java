package com.cat.service.controller;

import com.cat.service.entity.CatOwner;
import com.cat.service.exception_hanling.ThereIsNoSuchEntityException;
import com.cat.service.repository.OwnerRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;


    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation("Add owner")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Owner persisted")})
    public Long addCatOwner(@RequestBody CatOwner catOwner) {

        return ownerRepository
                .save(catOwner)
                .getOwner_id();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
