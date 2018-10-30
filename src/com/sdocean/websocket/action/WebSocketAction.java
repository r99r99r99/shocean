package com.sdocean.websocket.action;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.sdocean.websocket.service.MsgScoketHandle;
import com.sdocean.websocket.service.WebSocketHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketAction  implements WebSocketConfigurer{
	private static final Logger logger = Logger.getLogger(WebSocketAction.class);  
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		 System.out.println("==========================链接socket");
	        registry.addHandler(msgSocketHandle(),
	                "/webSocketServer.do").
	                addInterceptors(new WebSocketHandshakeInterceptor());
	        //浣跨敤socketjs鐨勬敞鍐屾柟娉�
	        registry.addHandler(msgSocketHandle(),
	                "/sockjs/webSocketServer.do").
	                addInterceptors(new WebSocketHandshakeInterceptor())
	                .withSockJS();
	}

	 /**
    *
    */ 
   @Bean(name = "msgSocketHandle")
   public WebSocketHandler msgSocketHandle(){
       return new MsgScoketHandle();
   }
}
