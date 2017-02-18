package com.engsoft29.bab.tweets.service;

import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import com.engsoft29.bab.tweets.dto.HashtagDTO;
import com.engsoft29.bab.tweets.exception.AppException;

@Stateless
public class HashtagService {

    public void processar(HashtagDTO dto) throws AppException {
    	validar(dto);
    	
    	//processar
    }
	
	private void validar(HashtagDTO dto) throws AppException {
	    if(dto == null) {
	        throw new AppException("SMS é obrigatório.");
	    }
	    
	    if(StringUtils.isBlank(dto.getHashtag())) {
	        throw new AppException("Uma hashtag é obrigatória.");
		}
	}
}