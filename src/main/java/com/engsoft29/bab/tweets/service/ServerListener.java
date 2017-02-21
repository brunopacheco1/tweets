package com.engsoft29.bab.tweets.service;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerListener extends Thread {

	@Override
	public void run() {
		try {
			ServerSocket listener = new ServerSocket(5522);
	        try {
	            while (true) {
	                Socket socket = listener.accept();
	                try {
	                	TweetService service = (TweetService) ServiceLocator.getInstance().lookup(TweetService.class);
	                    List<String> tweets = service.get();
	                    
	                    if(tweets != null) {
	                    	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	                    	
	                    	for(String tweet : tweets) {
	                    		out.println(tweet);
	                    	}
	                    }
	                } catch (Exception e) {
	                	e.printStackTrace();
					}finally {
	                    socket.close();
	                }
	            }
	        }
	        finally {
	            listener.close();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
