package com.mongodb.ps.tutorial.springboothandson.repository;

import com.mongodb.ps.tutorial.springboothandson.model.EmployeeCountByDept;
import com.mongodb.ps.tutorial.springboothandson.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, Long> {
    List<Employee> findByName(String name);

    // name 와 title 두개 조건을 생성  : { name : {0} , title : {0}
    List<Employee> findByNameAndTitle(String name, String title);

    List<Employee> findByAddress_City (String city) ;

    @Query( "{ 'name' : ?0 }")
    List<Employee> findCustomQuery ( String name );


    @Aggregation ( "{ $group : { _id : '$dept' , count : { $sum : 1 } }}")
    List<EmployeeCountByDept> groupByDept() ;

    List<Employee> findByName (String name, Pageable pageable) ;


    @Update( "{'$set' : { 'isActive' : ?1}}")
    void findAndIsActiveByName (String name, boolean isActive);

}


