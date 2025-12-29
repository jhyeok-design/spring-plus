package org.example.expert.domain.health.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> healthcheck(){
        log.info("health-check");
        return ResponseEntity.ok("ok");
    }
}
