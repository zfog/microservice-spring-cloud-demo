package com.zfog.rabbitmqservice.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg = "hello, i'm a message";
        System.out.println("Sender:" + msg);
        rabbitTemplate.convertAndSend("hello", msg);//将消息发送到名为“hello”的队列中
    }
}
