package com.mongodb.ps.tutorial.springboothandson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("employee")
@Getter
@Setter
@AllArgsConstructor
@ToString

public class Employee {

    @Id
    String id;

    String name ;

    String title;

    String dept;

    String email;

    String phoneNumber;

    Address address;

    long salary ;

    Boolean isActive;
}

