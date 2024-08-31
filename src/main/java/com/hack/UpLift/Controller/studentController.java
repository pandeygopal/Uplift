package com.hack.UpLift.Controller;
import com.hack.UpLift.Model.LoginRequest;
import com.hack.UpLift.Model.student;
import com.hack.UpLift.Services.RegisterStudent;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
@RequestMapping("/student")
public class studentController {
    @Autowired
    private RegisterStudent service;
    @PostMapping("/register")
    void register(@RequestBody student student) {
        service.registerStudent(student);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        System.out.println("login called");
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println("login called with email: " + email);
        boolean loginSuccessful = service.loginStudent(email, password, session);

        if (loginSuccessful) {
            System.out.println("session which is set "+ session.getId());
            session.setAttribute("LoginRequest",loginRequest );
            System.out.println("login hua");
            return ResponseEntity.status(HttpStatus.OK).body("std_dash.html");
        } else {
            System.out.println("login nahi hua");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    @GetMapping("/checkSession")
    public ResponseEntity<String> checkSession(HttpSession session) {
        System.out.println("session which is check "+ session.getId());
        if (session.getAttribute("LoginRequest") != null) {
            System.out.println("session vaild");
            // Session is valid
            return ResponseEntity.ok("Session valid");
        } else {
            System.out.println("session invaild");
            // Session is invalid
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session invalid");
        }
    }

}
