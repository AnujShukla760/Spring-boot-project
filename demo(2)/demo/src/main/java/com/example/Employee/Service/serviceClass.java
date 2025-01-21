package com.example.Employee.Service;

import com.example.Employee.Exception.ExceptionClass;
import com.example.Employee.Model.Employee;
import com.example.Employee.Repository.repositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class serviceClass {
    @Autowired
    private repositoryClass repositoryclass;
    public boolean saveEmployee(Employee employee) {
        if(repositoryclass.existsByName(String.valueOf(employee.getName()))) {
            throw new ExceptionClass("Employee with name" + employee.getName() + "Already Exists Status code = " + HttpStatus.CONFLICT);
        }else {
            repositoryclass.save(employee);

            return true;
        }
    }
    public Employee getEmployeeByName(String name) {
        return (Employee) repositoryclass.findByName(name).orElseThrow(() -> new RuntimeException("Employee with name " + name + " not found."));

    }

    public Employee findByName(String name) {
        return null;
    }

    public List<Employee> findAllEmployees() {
        return repositoryclass.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        if(repositoryclass.existsById(employee.getId())){
            return repositoryclass.save(employee);
        }else{
            return null;
        }
    }

    public boolean deleteEmployee(long id) {
        if(repositoryclass.existsById(id)){
            repositoryclass.deleteById(id);
            return true;
        }else {
            return false;
        }

    }
}

