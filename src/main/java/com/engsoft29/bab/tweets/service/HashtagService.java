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
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Singleton
public class HashtagService {
	
	private Set<String> hashtagsToTrack = new HashSet<>();
	
	private TwitterStream twitterStream;
	
	@PostConstruct
	private void init() {
		StatusListener listener = new StatusListener() {
			
			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
            public void onStatus(Status status) {
                System.out.println(status.getUser().getName() + " : " + status.getText());
            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        
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