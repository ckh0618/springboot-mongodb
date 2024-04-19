package com.mongodb.ps.tutorial.springboothandson.util;

import com.mongodb.ps.tutorial.springboothandson.model.Employee;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

public class TestEmployeeGenerator {

    @Test
    void testGenerator () {
        List<Employee> employees = EmployeeGenerator.generateEmployee(10);

        for ( Employee emp : employees) {

            System.out.println(emp);
        }
     }
}
