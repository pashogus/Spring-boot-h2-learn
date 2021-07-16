package com.pashogus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pashogus.model.Tag;
import com.pashogus.repository.TagRepository;

@RestController
@RequestMapping("/ap1/v2")
public class TagController {

	@Autowired
	TagRepository tagRepository;
	
	
	@GetMapping("/tags")
	public  List<Tag> gettags()
	{
		return tagRepository.findAll();
	}
	
	@PostMapping("/tags")
	public Tag createtags(@RequestBody Tag tag)
	{
		return tagRepository.save(tag);
	}
	
}
