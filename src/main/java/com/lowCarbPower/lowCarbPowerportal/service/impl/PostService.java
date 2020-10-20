package com.lowCarbPower.lowCarbPowerportal.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lowCarbPower.lowCarbPowerportal.config.ConfigProperties;
import com.lowCarbPower.lowCarbPowerportal.model.CommentModel;
import com.lowCarbPower.lowCarbPowerportal.model.PostModel;
import com.lowCarbPower.lowCarbPowerportal.service.IPostsService;

@Service
public class PostService implements IPostsService {
	
	private static final Logger log = LoggerFactory.getLogger(PostService.class);
	
    @Autowired
    private RestTemplate restTemplate;
    
	@Autowired
    private Environment environment;

	@Override
	public ArrayList<PostModel> getPosts() {
		String jsonPlaceholderTypiCodePostsApiUrl = environment.getProperty(ConfigProperties.JSON_PLACEHOLDER_TYPICODE_URL_POST);
		
		PostModel[] posts = restTemplate.getForObject(jsonPlaceholderTypiCodePostsApiUrl, PostModel[].class);
		ArrayList<PostModel> postsList = new ArrayList<>(Arrays.asList(posts));
		return postsList;
	}

	public ArrayList<CommentModel> getCommentsByPostId(int postId) {
		String jsonPlaceholderTypiCodeCommentsApiUrl = environment.getProperty(ConfigProperties.JSON_PLACEHOLDER_TYPICODE_URL_COMMENTS_FOR_POST);
		
		CommentModel[] comments = restTemplate.getForObject(jsonPlaceholderTypiCodeCommentsApiUrl + postId, CommentModel[].class);
		ArrayList<CommentModel> coomentsList = new ArrayList<>(Arrays.asList(comments));
				
		return coomentsList;
	}

}
