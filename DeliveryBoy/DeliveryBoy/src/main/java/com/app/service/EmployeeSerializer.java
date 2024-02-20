package com.app.service;

import com.app.entities.Employee;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class EmployeeSerializer implements Serializer<Employee> {


    @Override
    public byte[] serialize(String topic, Employee data) {

        if (data == null) {
            return null;
        }

        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             ObjectOutputStream objectStream = new ObjectOutputStream(byteStream)) {

            objectStream.writeObject(data);
            return byteStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error serializing Employee", e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
