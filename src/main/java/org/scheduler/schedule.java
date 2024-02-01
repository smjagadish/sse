package org.scheduler;


import com.fasterxml.jackson.databind.ObjectMapper;
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
    // avoid below code smell. use constructor injection probably
    @PostConstruct
    void initialize()
    {
        list = service.getClist();
    }
    @Scheduled(fixedRate = 1000)
    public void transmit() {
        // since we use cow list , guaranteed to avoid concurrent modif execpetions even if new connections/clients register
        for (SseEmitter e : list) {
            try {

                System.out.println(list.stream().count());
                e.send(service.getData(e));
            } catch (IOException err) {
                e.complete();
                // remove from list as client conn is probably dead
                list.remove(e);
                service.updateMap(e);
            }
        }

    }
}
