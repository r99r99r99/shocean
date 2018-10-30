package com.sdocean.websocket.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.common.collect.Lists;
import com.sdocean.users.model.SysUser;

@Component
public class MsgScoketHandle   implements  WebSocketHandler {
	
	/**已经连接的用户*/
    private static final ArrayList<WebSocketSession> users;
    
    static {
        //保存当前连接用户
        users = Lists.newArrayList();
    }

    /**
     * 断开链接
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        users.remove(webSocketSession);
        SysUser user = (SysUser) webSocketSession.getAttributes().get("user");
        System.out.println(user.getRealName()+"断开连接");
    }
	  /**
     * 建立链接
     * @param webSocketSession
     * @throws Exception
     */
	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		// TODO Auto-generated method stub
		//将用户信息存入到list中
		 users.add(webSocketSession);
	     System.out.println("=====================建立连接成功==========================");
	     SysUser user = (SysUser) webSocketSession.getAttributes().get("user");
	     if(user != null){
	        System.out.println("当前连接用户======"+user.getRealName());
	     }
	     System.out.println("webSocket连接数量====="+users.size());
	}
	/**
     * 接收消息
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
	@Override
	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
		// TODO Auto-generated method stub
		SysUser user = (SysUser) webSocketSession.getAttributes().get("user");
		System.out.println("收到用户:"+user.getRealName()+"的消息");
        System.out.println(webSocketMessage.getPayload().toString());
        System.out.println("===========================================");
	}
	/**
     * 异常处理
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
	@Override
	 public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable){
        if (webSocketSession.isOpen()){
            //关闭session
            try {
                webSocketSession.close();
            } catch (IOException e) {
            }
        }
        //移除用户
        users.remove(webSocketSession);
    }

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
     * 发送消息给指定的用户
     * @param user
     * @param messageInfo
     */
    public void sendMessageToUser(SysUser user, TextMessage messageInfo){
        for (WebSocketSession session : users) {
        	SysUser sessionUser = (SysUser) session.getAttributes().get("user");
            //根据用户名去判断用户接收消息的用户
            if(user.getId()==sessionUser.getId()){
                try {
                    if (session.isOpen()){
                        session.sendMessage(messageInfo);
                        System.out.println("发送消息给："+user.getRealName()+"内容："+messageInfo);
                    }
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
