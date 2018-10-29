package com.sdocean.websocket.service;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MsgScoketHandle   extends TextWebSocketHandler{
	
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("afterConnectionEstablished");
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	System.out.println("handleTextMessage");
        super.handleTextMessage(session, message);

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    	System.out.println("handleTransportError");
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	System.out.println("afterConnectionClosed");
        super.afterConnectionClosed(session, status);
    }
}
