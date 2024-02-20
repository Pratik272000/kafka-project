package com.app.service;

import com.app.entities.Employee;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

@Component
public class EmployeeDeserializer implements Deserializer<Employee> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }

    @Override
    public Employee deserialize(String topic, byte[] data) {
//        System.out.println("in deserialize Method of consumer");

        if (data == null) {
            return null;
        }

        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
             ObjectInputStream objectStream = new ObjectInputStream(byteStream)) {
//            System.out.println("in deserialize Method of consumer");

            return (Employee) objectStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error deserializing Employee", e);
        }
    }

    @Override
    public Employee deserialize(String topic, Headers headers, byte[] data) {
        // Implementing this method to handle headers if needed
        return deserialize(topic, data);
    }
}
