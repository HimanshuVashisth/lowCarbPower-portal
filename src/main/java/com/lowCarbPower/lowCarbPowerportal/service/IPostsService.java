package com.lowCarbPower.lowCarbPowerportal.service;

import java.util.ArrayList;

import com.lowCarbPower.lowCarbPowerportal.model.CommentModel;
import com.lowCarbPower.lowCarbPowerportal.model.PostModel;

public interface IPostsService {

	public ArrayList<PostModel> getPosts();
	
	public ArrayList<CommentModel> getCommentsByPostId(int postId);
	
}
