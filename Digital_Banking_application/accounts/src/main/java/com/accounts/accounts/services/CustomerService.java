package com.accounts.accounts.services;

import com.accounts.accounts.config.JwtService;
import com.accounts.accounts.controller.AuthRequest;
import com.accounts.accounts.controller.AuthResponse;
import com.accounts.accounts.controller.RegisterRequest;
import com.accounts.accounts.model.Customer;
import com.accounts.accounts.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var customer= Customer.builder()
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .createdAt(request.getCreatedAt())
                .email(request.getEmail())
                .passWord(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        repository.save(customer);
        var jwtToken=jwtService.generateToken(customer);
        return  AuthResponse.builder()
                .customerName(customer.getFullName())
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user =repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return  AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

/*
    public Customer createCustomer(String fullName, String email, String phoneNumber, String address, String passWord) {
        if (customerRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (customerRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        return customerRepository.save(customer);
    }
*/

    public Customer getCustomerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }
}