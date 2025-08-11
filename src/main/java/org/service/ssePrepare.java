package org.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.data.messageData;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ssePrepare {
    private CopyOnWriteArrayList<SseEmitter> clist = new CopyOnWriteArrayList<>();
    private Map<SseEmitter, messageData> map = new HashMap<>();
    public SseEmitter createEmitter()
    {
        // create a unique emitter instance per connection/domain
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        // add this to a list to send events at a fixed interval
        clist.add(emitter);
        messageData md = new messageData();
        md.setId(100);
        md.setName("temporary user");
        map.put(emitter,md);
        return emitter;
    }
    public CopyOnWriteArrayList<SseEmitter> getClist()
    {
        return clist;
    }
    public void updateMap(SseEmitter e)
    {
        map.remove(e);
    }
    public messageData getData(SseEmitter e)
    {
        return map.get(e);
    }
}
