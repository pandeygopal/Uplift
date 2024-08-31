package com.hack.UpLift.Repo;

import com.hack.UpLift.Model.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepo extends JpaRepository<student, String> {
}
