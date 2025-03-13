package com.user.user.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("api/v1/auth")
    @AllArgsConstructor
    public class UserController {
        private final AuthService service;

        @PostMapping("/register")
        public ResponseEntity<AuthResponse> register(
                @RequestBody RegisterRequest request) {

            return ResponseEntity.ok(service.register(request));
        }

        @PostMapping("/authenticate")
        public ResponseEntity<AuthResponse> authenticate(
                @RequestBody AuthRequest request) {

            return ResponseEntity.ok(service.authenticate(request));
        }
}
