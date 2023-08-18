package com.example.day4.services;

import com.example.day4.entities.Customer;
import com.example.day4.entities.Product;
import com.example.day4.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;
    final TinkEncDec tinkEncDec;

    final HttpServletRequest req;

    final ModelMapper modelMapper;

    public ResponseEntity save(Customer customer) {
        try {
            String newPass = tinkEncDec.encrypt(customer.getPassword());
            customer.setPassword(newPass);
            customerRepository.save(customer);
            return new ResponseEntity(customer, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(customer, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity login(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(customer.getEmail());
        if (optionalCustomer.isPresent()) {
            Customer c = optionalCustomer.get();
            String newPass = tinkEncDec.decrypt(c.getPassword());
            if (newPass.equals(customer.getPassword())) {
                //login success
                //session create
                req.getSession().setAttribute("user",c);
             //   Product.CustomerDto dto=modelMapper.map(c, Product.CustomerDto.class);
                return new ResponseEntity(c, HttpStatus.OK);
            }
        }
        return new ResponseEntity("email or password Error", HttpStatus.BAD_REQUEST);
    }
}

