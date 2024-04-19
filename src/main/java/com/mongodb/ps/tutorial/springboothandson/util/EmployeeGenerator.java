package com.mongodb.ps.tutorial.springboothandson.util;

import com.mongodb.ps.tutorial.springboothandson.model.Address;
import com.mongodb.ps.tutorial.springboothandson.model.Employee;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EmployeeGenerator {

    public static List<Employee> generateEmployee(int n) {

        List employees = new ArrayList<Employee>();

        for (int i = 0; i < n; i++) {
            String[] titles = {"사원", "대리", "과장", "차장", "부장"};
            String[] depts = {"부서1", "부서2", "부서3"};

            Faker faker = new Faker(new Locale("ko"));
            Faker faker2 = new Faker(new Locale("en"));

            //String id = faker.random().hex();
            String name = faker.name().fullName().replaceAll(" ", "");
            String phone = faker.phoneNumber().cellPhone();

            String title = titles[faker.number().numberBetween(0, titles.length)];
            String dept = depts[faker.number().numberBetween(0, depts.length)];

            String email = faker2.lorem().word() + "@" + "samsung.com";
            Address address = new Address("대한한국", faker.address().cityName(), faker.address().fullAddress());

            Employee emp = new Employee(null, name, title, dept, email, phone, address, 100, false);


            employees.add(emp);
        }


        return employees;
    }
}
