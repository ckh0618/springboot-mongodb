package com.mongodb.ps.tutorial.springboothandson.service;

import com.mongodb.ps.tutorial.springboothandson.model.Employee;
import com.mongodb.ps.tutorial.springboothandson.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void registerTwoEmployees(Employee emp1, Employee emp2) {
        employeeRepository.save(emp1);

        // 예제를 위한 간단한 검증 로직. emp2의 이름이 null이면 예외 발생
        if (emp2.getName() == null) {
            throw new RuntimeException("Employee's name cannot be null");
        }

        employeeRepository.save(emp2);
    }
}
