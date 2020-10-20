package com.lowCarbPower.lowCarbPowerportal.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowCarbPower.lowCarbPowerportal.exception.RestApiException;
import com.lowCarbPower.lowCarbPowerportal.exception.ThirdPartyException;
import com.lowCarbPower.lowCarbPowerportal.model.CommentModel;
import com.lowCarbPower.lowCarbPowerportal.model.PostModel;
import com.lowCarbPower.lowCarbPowerportal.service.impl.PostService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600)
@RestController
@RequestMapping("/posts")
public class PostsController {
	
	private final Logger log = LoggerFactory.getLogger(PostsController.class);
	
	@Autowired
	private PostService postService;
	
    @ApiOperation(value = "JSON PLACEHOLDER GET POSTS Service")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ArrayList<PostModel>> fetchPosts() throws ThirdPartyException, IOException, RestApiException {
    	log.info("Posts Service");    	
    	ArrayList<PostModel> posts = postService.getPosts();
    	
        if (posts != null) {        	
            return ResponseEntity.ok(posts);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
    
    @ApiOperation(value = "JSON PLACEHOLDER GET Comments for a post Service")
    @RequestMapping(value = "/comments",method = RequestMethod.GET)
    public ResponseEntity<ArrayList<CommentModel>> fetchCommentsByPostId(@RequestParam(value="postId") int postId) throws ThirdPartyException, IOException, RestApiException {    
    	log.info("Comments Service");    	
    	ArrayList<CommentModel> comments = postService.getCommentsByPostId(postId);    	
    	
        if (comments != null) {        	
            return ResponseEntity.ok(comments);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

}
