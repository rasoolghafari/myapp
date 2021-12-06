package ir.rasoolghafari.myapp.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController("/hello")
public class HelloResource {

    @GetMapping
    @PreAuthorize("hasRole('ACL')")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.of(Optional.of("Hello at" + new Date()));
    }
}
