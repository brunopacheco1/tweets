package com.engsoft29.bab.tweets.service;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

import org.apache.commons.lang3.StringUtils;

import com.engsoft29.bab.tweets.dto.HashtagDTO;
import com.engsoft29.bab.tweets.exception.AppException;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Singleton
public class HashtagService {
	
	private Set<String> hashtagsToTrack = new HashSet<>();
	
	private TwitterStream twitterStream;
	
	@PostConstruct
	private void init() {
		StatusListener listener = new TweetConsumer();
        
        twitterStream = new TwitterStreamFactory().getInstance();
        
        twitterStream.addListener(listener);
	}
	
	@PreDestroy
	private void destroy() {
		twitterStream.shutdown();
	}

    public void processar(HashtagDTO dto) throws AppException {
    	validar(dto);
    	
    	hashtagsToTrack.add(dto.getHashtag());
    	
    	twitterStream.shutdown();
    	
        FilterQuery filter = new FilterQuery();
        filter.track(hashtagsToTrack.toArray(new String [] {}));
        filter.language(new String[]{"pt"});
        twitterStream.filter(filter);
    }
	
	private void validar(HashtagDTO dto) throws AppException {
	    if(dto == null) {
	        throw new AppException("Hashtag é obrigatória.");
	    }
	    
	    if(StringUtils.isBlank(dto.getHashtag()) || !dto.getHashtag().startsWith("#")) {
	        throw new AppException("Uma hashtag é obrigatória.");
		}
	}
}