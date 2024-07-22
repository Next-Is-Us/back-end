package com.nextisus.project.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Slf4j
public class TestController implements TestControllerApi {

    @GetMapping
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("GET SUCCESS");
    }
}
