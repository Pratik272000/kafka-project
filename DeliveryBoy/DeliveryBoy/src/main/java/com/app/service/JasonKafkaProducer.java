package com.app.service;

import com.app.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JasonKafkaProducer {
public static final Logger LOGGER= LoggerFactory.getLogger(JasonKafkaProducer.class);
@Autowired
private KafkaTemplate<String, User> kafkaTemplate;

public void sendMessage(User user){
    Message<User> message= MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC,"javaguiders")
            .build();
    kafkaTemplate.send(message);
}

}
