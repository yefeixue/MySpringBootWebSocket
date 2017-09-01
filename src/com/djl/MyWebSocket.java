package com.djl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
@ServerEndpoint(value = "/websocket")//,configurator=GetHttpSessionConfigurator.class
public class MyWebSocket{

	private static Logger logger = LoggerFactory.getLogger(MyWebSocket.class);
	
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	public static CopyOnWriteArraySet<Session> webSocketSet = new CopyOnWriteArraySet<Session>();
	//用于缓存接收到的消息
	public static Map<String,String> messageMap = new HashMap<String,String>();
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	
	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session,EndpointConfig config) {
		this.session = session;
		webSocketSet.add(session); // 加入set中
		addOnlineCount(); // 在线数加1
		session.setMaxBinaryMessageBufferSize(1 * 1024 * 1024); // 1MB
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		System.out.println("来自客户端的二进制数据--" + message.length());
		try {
			System.out.println("message:"+message);
			messageMap.put(message, message);
			session.getBasicRemote().sendText("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("发送成功！");
	}

	/**
	 * 发生错误时调用
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		MyWebSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		MyWebSocket.onlineCount--;
	}

}
