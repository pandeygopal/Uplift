package com.hack.UpLift.Services;

import com.hack.UpLift.Model.alumni;
import com.hack.UpLift.Model.student;
import com.hack.UpLift.Repo.alumniRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterAlumni {
    @Autowired
    private alumniRepo repo;
    public void registerAlumni(alumni alumni) {
        repo.save(alumni);
    }

    public boolean loginAlumni(String email, String password) {
        Optional<alumni> foundAlumni = repo.findById(email);

        if (foundAlumni.isPresent()) {
            alumni alumni = foundAlumni.get();
            if (alumni.getPassword().equals(password)) {
                System.out.println("You are logged in");
                return true;
            } else {
                System.out.println("Incorrect password");
                return false;
            }
        } else {
            System.out.println("Alumni not found");
            return false;
        }
    }
}