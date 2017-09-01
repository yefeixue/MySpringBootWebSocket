package com.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class TestClient {

	public static void main(String[] args) {
		Session session = null;
		try {
			WebSocketContainer container = ContainerProvider
					.getWebSocketContainer(); // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
			String uri = "ws://localhost:8080/websocket";//"ws://192.168.2.80:8080/websocket";
			URI newuri = new URI(uri);  
			session = container.connectToServer(Client.class,  newuri); // 连接会话
			session.getBasicRemote().sendText("type:cameraInterval,interval:25000");

		} catch (Exception e) {
			e.printStackTrace();
		}
		int i = 0;
		while (i++ < 2) {
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
	
    public static byte[] subBytes(byte[] src, int begin, int count) {  
        byte[] bs = new byte[count];  
        System.arraycopy(src, begin, bs, 0, count);  
        return bs;  
    }  

}
