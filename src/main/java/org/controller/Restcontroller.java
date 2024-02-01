package org.controller;

import org.scheduler.schedule;
import org.service.ssePrepare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Callable;

@Controller
public class Restcontroller {

    private SseEmitter sse = new SseEmitter();
    @Autowired
    ssePrepare sseService;
    @GetMapping("sse")
    @CrossOrigin
    public SseEmitter handle()  {
            // service returns an sse emitter instance to the client
            SseEmitter e = sseService.createEmitter();
            try {
                e.send("first data");
                return e;
            }
            catch(IOException err)
            {
                // if this happens how will the client handle the null return ? is there a better way to do this ?
                e.complete();
                sseService.getClist().remove(e);
                return null;
            }

    }

}
