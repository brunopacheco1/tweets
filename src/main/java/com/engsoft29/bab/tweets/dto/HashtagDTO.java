package com.engsoft29.bab.tweets.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HashtagDTO implements Serializable {

	private static final long serialVersionUID = 6435974476117737767L;
	
    @ApiModelProperty(value="Hashtag a ser processada.", example="#hashtag")
	private String hashtag;

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
}