package com.hyunho9877.outsource.utils.websocket;

import com.hyunho9877.outsource.domain.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebsocketEventListener {

    private Map<String, Subscription> subscriptionMap = new ConcurrentHashMap<>();

    @EventListener
    public void onConnect(SessionConnectedEvent event) {
        log(event);
    }

    @EventListener
    public void onDisconnected(SessionDisconnectEvent event) {
        log(event);
    }

    @EventListener
    public void onSubscribe(SessionSubscribeEvent event) {
        MessageHeaders headers = event.getMessage().getHeaders();
        String subscriptionId = String.valueOf(headers.get("simpSubscriptionId"));
        String sessionId = String.valueOf(headers.get("simpSessionId"));
        List<String> destination = (List<String>) headers.get("destination");
        log.info("subscriptionId : {}", subscriptionId);
        log.info("sessionId : {}", sessionId);
        log.info("destination : {}", destination);
        log(event);
    }

    @EventListener
    public void onUnsubscribe(SessionUnsubscribeEvent event) {

        log(event);
    }

    @EventListener
    public void onConnect(SessionConnectEvent event) {
        log(event);
    }

    private void log(AbstractSubProtocolEvent event) {
        Message<byte[]> message = event.getMessage();
        Object source = event.getSource();

        log.info("session message : {}", message);
        log.info("session source : {}", source);
    }
}
