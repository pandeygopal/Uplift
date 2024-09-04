package com.hack.UpLift.Controller;
import com.hack.UpLift.Model.LoginRequest;
import com.hack.UpLift.Model.Post;
import com.hack.UpLift.Model.alumni;
import com.hack.UpLift.Services.PostService;
import com.hack.UpLift.Services.RegisterAlumni;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/", allowCredentials = "true")
@RequestMapping("/alumni")
public class alumniController {
    @Autowired
    private RegisterAlumni service;

    @PostMapping("/register")
    void register(@RequestBody alumni alumni) {
        service.registerAlumni(alumni);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        System.out.println("login called");
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println("login called with email: " + email);
        boolean loginSuccessful = service.loginAlumni(email, password, session);

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
    @Autowired
    PostService postService;

    @PostMapping("/createPost")
    public ResponseEntity<?> addPost(@RequestPart Post post,
                                     @RequestPart MultipartFile imageFile) {
        System.out.println("create post called");
        try {
            if (imageFile.isEmpty()) {
                System.out.println("image file is empty");
                return new ResponseEntity<>("Image file is required", HttpStatus.BAD_REQUEST);

            }

            Post createdPost = postService.addPost(post, imageFile);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Error processing the image file", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}