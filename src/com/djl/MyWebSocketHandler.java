//package com.djl;
//
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//public class MyWebSocketHandler extends TextWebSocketHandler{
//
//	
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		// TODO Auto-generated method stub
//		super.afterConnectionEstablished(session);
//		System.out.println("------------------------------");
//	}
//
//	@Override
//	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		// TODO Auto-generated method stub
//		super.afterConnectionClosed(session, status);
//		System.out.println("+++++++++++++++++++++++++++++");
//	}
//
//	@Override
//	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		System.out.println("接受到客户端消息"+message.toString());
//		session.sendMessage(new TextMessage("测试通过~~~~~~~~~~~~~"));
//	}
//
//}
