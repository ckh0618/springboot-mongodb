package com.mongodb.ps.tutorial.springboothandson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@AllArgsConstructor
@ToString
public class EmployeeCountByDept {
    @Id
    String dept;
    long count;
}
