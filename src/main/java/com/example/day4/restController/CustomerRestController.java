package com.example.day4.restController;

import com.example.day4.services.CustomerService;
import com.example.day4.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerRestController {

    final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody Customer customer){
        return customerService.save(customer);

    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Customer customer){
        return customerService.login(customer);
    }
}

