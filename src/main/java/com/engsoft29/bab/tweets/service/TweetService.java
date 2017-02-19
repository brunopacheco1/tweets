package com.engsoft29.bab.tweets.service;

import javax.ejb.Stateless;

@Stateless
public class TweetService {

	public void processar(String tweet) {
		System.out.println(tweet);
	}
}