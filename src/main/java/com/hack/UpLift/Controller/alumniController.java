package com.hack.UpLift.Controller;
import com.hack.UpLift.Model.alumni;
import com.hack.UpLift.Model.student;
import com.hack.UpLift.Services.RegisterAlumni;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
@RequestMapping("/alumni")
public class alumniController {
    @Autowired
    private RegisterAlumni service;
    @PostMapping("/register")
    void register(@RequestBody alumni alumni) {
        service.registerAlumni(alumni);
    }

    @PostMapping("/login/{email}/{password}")
    public ResponseEntity<String> login(@PathVariable String email, @PathVariable String password) {

        boolean loginSuccessful = service.loginAlumni(email, password);

        if (loginSuccessful) {
            return ResponseEntity.ok("You are logged in");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
