package com.twitter.request;

import java.util.List;

import lombok.Data;

@Data
public class HashTagRequest {

	private List<String> hashTags;
	private List<Long> hashNumber;
}
