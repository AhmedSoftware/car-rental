package com.ahmedsoftware.carrental.listenner;

import com.ahmedsoftware.carrental.pojo.Fraud;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FraudListennerTest {
    @Autowired
    FraudListenner fraudListenner;
    
    @Autowired
    private Sink sink;
    
    @Test
    public void test_should_consume_fraud_message() throws Throwable{
        
        String sinkMessage = "sinkMessage";
        final Message<Fraud> message = MessageBuilder.withPayload(new Fraud(sinkMessage)).build();
        SubscribableChannel input = sink.input();
        input.send(message);
    
        BDDAssertions.then(this.fraudListenner.name).isEqualTo(sinkMessage);
    }
}