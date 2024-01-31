package org.scheduler;


import lombok.Data;
import org.service.ssePrepare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
@Data
public class schedule {
    @Autowired
    private ssePrepare service;
    private CopyOnWriteArrayList<SseEmitter> list ;
    schedule()
    {

    }
    @PostConstruct
    void initialize()
    {
        list = service.getClist();
    }
    @Scheduled(fixedRate = 1000)
    public void transmit() throws IOException {
        for (SseEmitter e : list) {
            System.out.println(list.stream().count());
            e.send("more data coming through");
        }
    }
}
