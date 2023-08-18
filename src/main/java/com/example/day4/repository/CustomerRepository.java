package com.example.day4.repository;

import com.example.day4.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailEqualsIgnoreCase(String email);

}
