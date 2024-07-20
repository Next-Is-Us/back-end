package com.nextisus.project.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    @GetMapping
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("GET SUCCESS");
    }

    @PostMapping
    public ResponseEntity<String> postTest() {
        return ResponseEntity.ok("POST SUCCESS");
    }

    @PutMapping
    public ResponseEntity<String> putTest() {
        return ResponseEntity.ok("PUT SUCCESS");
    }

    @PatchMapping
    public ResponseEntity<String> patchTest() {
        return ResponseEntity.ok("PATCH SUCCESS");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTest() {
        return ResponseEntity.ok("DELETE SUCCESS");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloTest() {
        return ResponseEntity.ok("HELLO SUCCESS");
    }
}
