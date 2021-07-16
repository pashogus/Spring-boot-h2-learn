package com.pashogus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pashogus.model.Post;
import com.pashogus.repository.PostRepository;

@RestController
@RequestMapping("/api/v1")
public class PostController {

	@Autowired
	PostRepository postRepository;
	
	@GetMapping("/Post")
	public List <Post> getPost()
	{
		return postRepository.findAll();
	}
	
	@GetMapping("/Post/{id}")
	public ResponseEntity <Post> getPost(@PathVariable (value="id") Long postID) throws ResourceNotFoundException
	{
		Post post= postRepository.findById(postID)
		 .orElseThrow(() -> new ResourceNotFoundException("Post not found :: " + postID));
		return ResponseEntity.ok().body(post);
		
	}
	
	 @PostMapping("/Post")
	    public Post createPost(  @RequestBody Post post) {
	    	//below two lines are not required as we are already sending data in JSON format
	    	//Instructor instructornew = new Instructor(instructor.getFirstName(),instructor.getLastName(),instructor.getEmail());
	    	//instructornew.setCourses(instructor.getCourses());
	      return postRepository.save(post);
	    }
	
	
}
