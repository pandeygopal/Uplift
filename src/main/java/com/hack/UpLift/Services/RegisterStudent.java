package com.hack.UpLift.Services;
import com.hack.UpLift.Model.student;
import com.hack.UpLift.Repo.studentRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterStudent {
    @Autowired
private studentRepo repo;
    public void registerStudent(student student) {
        repo.save(student);

    }

    public boolean loginStudent(String email, String password,HttpSession session) {
        Optional<student> foundStudent = repo.findById(email);
        if (foundStudent.isPresent()) {
            student student = foundStudent.get();
            if (student.getPassword().equals(password)) {
                System.out.println("You are logged in");
                return true;
            } else {
                System.out.println("Incorrect password");
                return false;
            }
        } else {
            System.out.println("Student not found");
            return false;
        }
    }

}
