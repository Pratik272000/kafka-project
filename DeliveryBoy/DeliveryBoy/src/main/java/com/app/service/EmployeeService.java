package com.app.service;

import com.app.config.AppConstant;
import com.app.dao.EmployeeDao;
import com.app.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    KafkaTemplate<String, byte[]> kafkaTemplate;
    public ResponseEntity<?> addEmployee(Employee employee) {
        EmployeeSerializer employeeSerializer=new EmployeeSerializer();
        byte[] b=employeeSerializer.serialize(AppConstant.topicName,employee);
        kafkaTemplate.send(AppConstant.topicName,"sponser.sensor.name", b);
        return ResponseEntity.ok("Employee send");
    }
}
