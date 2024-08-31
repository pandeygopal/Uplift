package com.hack.UpLift.Repo;
import com.hack.UpLift.Model.alumni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface alumniRepo extends JpaRepository<alumni, String> {
}
