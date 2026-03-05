package com.sprinboot.web_tutorial.repo;

import com.sprinboot.web_tutorial.entity.EmployeeEntity;
import com.sprinboot.web_tutorial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

//    Optional<User> FindByEmail();

}
