package com.test;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.springframework.context.ApplicationContext;

@ClientEndpoint
public class Client {
	
	private static ApplicationContext applicationContext;
	
	public static void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}
	//接收树莓派返回的消息
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
    }

    @OnMessage
    public void onMessage(String message) {
//    	RaspCtrClient raspCtrClient = applicationContext.getBean(RaspCtrClient.class);
////    	RaspCtrClient raspCtrClient = new RaspCtrClient();
//        System.out.println(message);
//        raspCtrClient.messageFromRaspServer = message;
    }
    
    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
    
    @OnClose
    public void close(Session session){
    	System.out.println("客户端要关闭了");
    }
}