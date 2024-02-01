package org.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.CopyOnWriteArrayList;
@Service
public class ssePrepare {
    private CopyOnWriteArrayList<SseEmitter> clist = new CopyOnWriteArrayList<>();
    public SseEmitter createEmitter()
    {
        // create a unique emitter instance per connection/domain
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        // add this to a list to send events at a fixed interval
        clist.add(emitter);
        return emitter;
    }
    public CopyOnWriteArrayList<SseEmitter> getClist()
    {
        return clist;
    }
}
