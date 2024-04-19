package com.mongodb.ps.tutorial.springboothandson.controller;

import com.mongodb.ps.tutorial.springboothandson.model.Employee;
import com.mongodb.ps.tutorial.springboothandson.repository.EmployeeRepository;
import com.mongodb.ps.tutorial.springboothandson.util.EmployeeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository repo ;

    @PostMapping("/employee")
    Employee newEmployee (@RequestBody Employee emp) {
        return repo.save(emp);
    }

    @GetMapping("/employee/{id}")
    Employee getOne(@PathVariable Long id ) {
        return repo.findById(id).orElseThrow();
    }

    @GetMapping("/employee/generate/{n}")
    List<Employee> generateEmployees ( @PathVariable int n) {

        List<Employee> employees = EmployeeGenerator.generateEmployee(n);

        repo.saveAll(employees);

        return employees;
    }

    @PutMapping("/employee/find/{name}")
    List<Employee> getEmployeeByName ( @PathVariable String name) {
        List<Employee> employees = repo.findByName(name);
        return employees;
    }

}
