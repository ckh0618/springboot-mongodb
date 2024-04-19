package com.mongodb.ps.tutorial.springboothandson.template;

import com.mongodb.client.MongoClient;
import com.mongodb.ps.tutorial.springboothandson.model.Address;
import com.mongodb.ps.tutorial.springboothandson.model.Employee;
import com.mongodb.ps.tutorial.springboothandson.model.EmployeeCountByDept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SetOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@SpringBootTest
public class MongoTemplateTest {

    @Autowired
    MongoTemplate mongo ;

    @Test
    void runInsert () {

        mongo.getCollection("employee").drop();

        Address address = new Address ( "서울", "한국", "주소1");
        Employee contact = new Employee(null, "TestName", "Manager",
                "부서1", "test@mongodb.com", "010-0000-0000",
                address, 10000, true );
        mongo.insert ( contact );

        // Find with Query
        Query query = new Query();
        query.addCriteria(Criteria.where("dept").is("부서1"));
        Employee result = mongo.findOne(query, Employee.class, "employee");
        System.out.println(result);

        // Update
        Query query2 = new Query ();
        query2.addCriteria(Criteria.where("_id").is(result.getId()));
        Update update = new Update () ;
        update.set("isActive", false );
        mongo.updateFirst(query2, update, Employee.class);


        // Delete
      //  mongo.remove(result);
    }

    @Test
    void aggregationExample () {

        GroupOperation groupByDepartment = Aggregation.group("dept").count().as("count");
        Aggregation aggregation = Aggregation.newAggregation(groupByDepartment);
        AggregationResults<EmployeeCountByDept> result = mongo.aggregate(aggregation, "employee", EmployeeCountByDept.class);

        List<EmployeeCountByDept> list = result.getMappedResults();

        for (EmployeeCountByDept dept : list) {
            System.out.println(dept);
        }


    }
}
