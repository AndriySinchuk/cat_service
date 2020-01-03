package com.cat.service.controller;

import com.cat.service.entity.Doctor;
import com.cat.service.exception_hanling.DuplicateEntityException;
import com.cat.service.repository.DoctorRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("Add doctor or update by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Breed persisted"),
            @ApiResponse(code = 403, message = "Duplicate doctor not allowed")})
    public ResponseEntity addDoctor(@RequestBody Doctor doctor) throws DuplicateEntityException {
        try {
            doctorRepository.save(doctor);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEntityException();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
    @ApiOperation("Retrieve all doctors")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Info retrieved")})
    public Iterable<Doctor> readDoctors() {
        return doctorRepository.findAll();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete doctor by id")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Deleted by id")})
    public ResponseEntity deleteCatById(@PathVariable("id") Long id) {
        doctorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
