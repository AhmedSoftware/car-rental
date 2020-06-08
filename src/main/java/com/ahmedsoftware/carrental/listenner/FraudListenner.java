package com.ahmedsoftware.carrental.listenner;


import com.ahmedsoftware.carrental.pojo.Fraud;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FraudListenner {
    String name;
    
    @StreamListener(Sink.INPUT)
    public void onFraudMessage(Fraud fraud){
        this.name = fraud.getName();
        log.info("NAME : {}",this.name);
    }
    
}
