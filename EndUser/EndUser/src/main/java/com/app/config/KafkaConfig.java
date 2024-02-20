package com.app.config;

import com.app.dao.EmployeeDao;
import com.app.entities.Employee;
import com.app.service.EmployeeDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {
@Autowired
    EmployeeDao employeeDao;
    @KafkaListener(topics = AppConstant.topicName,groupId = AppConstant.GROUP_ID)
    public void addEmployee(byte [] arr){
        EmployeeDeserializer employeeDeserializer=new EmployeeDeserializer();
//        System.out.println("in consumer listner method");
        System.out.println(arr.length);
        Employee emp= employeeDeserializer.deserialize(AppConstant.topicName,arr);
       Employee e=employeeDao.save(emp);
       if(e!=null)
           System.out.println("Employee stored in Database");
       else
           System.out.println("doesnt get stored in database");
        System.out.println(emp);

    }

}
