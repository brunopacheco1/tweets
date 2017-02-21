package com.engsoft29.bab.tweets.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class TweetService {
	
	private List<String> tweets = new ArrayList<>();
	
	@PostConstruct
	private void init() {
		Thread thread = new ServerListener();
		thread.start();
	}

	public void add(String tweet) throws IOException {
		tweets.add(tweet);
    }
	
	public List<String> get() {
		if(tweets.isEmpty()) {
			return null;
		}
		
		List<String> result = tweets;
		
		tweets = new ArrayList<>();
		
		return result;
	}
}