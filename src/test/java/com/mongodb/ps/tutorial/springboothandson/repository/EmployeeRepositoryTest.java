package com.mongodb.ps.tutorial.springboothandson.repository;

import com.mongodb.ps.tutorial.springboothandson.model.Address;
import com.mongodb.ps.tutorial.springboothandson.model.EmployeeCountByDept;
import com.mongodb.ps.tutorial.springboothandson.model.Employee;
import com.mongodb.ps.tutorial.springboothandson.service.EmployeeService;
import com.mongodb.ps.tutorial.springboothandson.util.EmployeeGenerator;
import net.bytebuddy.description.annotation.AnnotationValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository repo;

    @Autowired
    EmployeeService employeeService;


    @Test
    void testSimpleDocumentCreate () {
        repo.deleteAll();

        Address address = new Address ( "서울", "한국", "주소1");
        Employee contact = new Employee(null, "TestName", "부서",
                "Manager", "test@mongodb.com", "010-0000-0000",
                address, 10000, true );

        repo.save(contact);

        System.out.println(contact);
    }

    @Test
    void populateEmployee () {
        repo.deleteAll();
        List<Employee> employees = EmployeeGenerator.generateEmployee(10000);
        //employees.forEach( emp -> repo.save(emp));

        repo.saveAll(employees);
    }

    @Test
    void findEmployeeByName () {

        List<Employee> emp = repo.findByName("송하은");

        System.out.println("=========");
        System.out.println(emp);
        System.out.println("=========");

    }

    @Test
    void findEmployeeByNameAndTitle () {
        List <Employee> emp = repo.findByNameAndTitle("송하은", "카카");
        System.out.println("=========");
        System.out.println(emp);
        System.out.println("=========");
    }

    @Test
    void findEmployeeByAddress_City () {
        List <Employee> emp = repo.findByAddress_City("의왕");
        System.out.println("=========");
        System.out.println(emp);
        System.out.println("=========");
    }

    @Test
    void findEmployeeCustomQuery () {
        List <Employee> emp = repo.findCustomQuery("임수빈");
        System.out.println("=========");
        System.out.println(emp);
        System.out.println("=========");
    }

    @Test
    void aggregationEmployeeCountByDept () {
        List<EmployeeCountByDept> dept= repo.groupByDept();
        System.out.println("=========");
        System.out.println(dept);
        System.out.println("=========");
    }

    @Test
    void getAllEmployeeUsingPage () {
        Sort sort = Sort.by("salary").descending().and(Sort.by("name").ascending());
        //PageRequest pageRequest = PageRequest.of(1, 20);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
        List<Employee> employees = repo.findByName("홍길동",pageable  );
        System.out.println("=========");
        System.out.println(employees);
        System.out.println("=========");
    }


    @Test
    void transactionTest () {

        Address address = new Address ( "서울", "한국", "주소1");
        Employee emp1 = new Employee(null, "Alice", "부서",
                "Manager", "test@mongodb.com", "010-0000-0000",
                address, 10000, true );


        Address address2 = new Address ( "서울", "한국", "주소1");
        Employee emp2 = new Employee(null, null, "부서",
                "Manager", "test@mongodb.com", "010-0000-0000",
                address2, 10000, true );

        try {
            employeeService.registerTwoEmployees(emp1, emp2);
        } catch (RuntimeException e) {
            System.err.println("Regisration Faield : " + e.getMessage()) ;
        }

        System.out.println("Transaction Success !");
    }

    @Test
    void observeSave () {

        Sort sort = Sort.by("salary").descending().and(Sort.by("name").ascending());
        //PageRequest pageRequest = PageRequest.of(1, 20);
        Pageable pageable = PageRequest.of(0, 1, Sort.by("name"));
        List<Employee> employees = repo.findByName("김준서",pageable  );

        Employee emp = employees.get(0);
        emp.setIsActive(false);
        repo.save(emp);

        repo.findAndIsActiveByName("김준서", true );
    }


}
