package com.cat.service.controller;

import com.cat.service.entity.Customer;
import com.cat.service.exception_hanling.ThereIsNoSuchEntityException;
import com.cat.service.repository.CustomerRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(value = "/add_customer", method = RequestMethod.POST)
    @ApiOperation("Add customer or update by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Owner persisted")})
    public Long addCatOwner(@RequestBody Customer customer) {

        return customerRepository
                .save(customer)
                .getCustomer_id();
    }

    @RequestMapping(value = "/get_customer_by_full_name"
            ,params = {"name","ownerSecondName"}
            ,method = RequestMethod.GET)
    @ApiOperation("Retrieve customer by name and second name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer retrieved")})
    public Customer findCatOwnerByOwnerNameAndOwnerSecondName(@RequestParam String name, @RequestParam String ownerSecondName) {

        return customerRepository.findCustomerByCustomerNameAndCustomerSecondName(name, ownerSecondName);
    }

    @RequestMapping(value = "/delete{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer deleted")})
    public ResponseEntity deleteCatOwnerById(@PathVariable("id") Long id) {
        customerRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ApiOperation("Retrieve customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Info retrieved"),
            @ApiResponse(code = 404, message = "Not found")})
    public Customer readOwner(@PathVariable("id") Long id) {
        return customerRepository
                .findById(id)
                .orElseThrow(ThereIsNoSuchEntityException::new);
    }
}
