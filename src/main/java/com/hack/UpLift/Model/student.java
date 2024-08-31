package com.hack.UpLift.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class student {
    @Id
    String email;
    String name;
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
