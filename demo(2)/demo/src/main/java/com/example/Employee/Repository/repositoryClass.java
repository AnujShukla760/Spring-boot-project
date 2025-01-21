package com.example.Employee.Repository;

import com.example.Employee.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface repositoryClass extends JpaRepository<Employee, Long> {


    boolean existsByName(String employee);

    <T> ScopedValue<T> findByName(String name);
}
