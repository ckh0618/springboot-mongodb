package com.mongodb.ps.tutorial.springboothandson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Address {

    String country;
    String city ;
    String address1;
}
