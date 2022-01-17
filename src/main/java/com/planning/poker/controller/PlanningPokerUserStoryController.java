package com.planning.poker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planning.poker.exception.UserStoryNotFoundException;
import com.planning.poker.model.UserStory;
import com.planning.poker.service.UserStoryService;

@RestController
@RequestMapping("/planningPoker/userstory")
public class PlanningPokerUserStoryController {

	Logger logger = LoggerFactory.getLogger(PlanningPokerUserStoryController.class);
	
	@Autowired
	UserStoryService userStoryService;
	
	
	@PostMapping("/addUserStory")
	public ResponseEntity<UserStory> addUserStory(@RequestParam String uStoryId,@RequestParam String description,@RequestParam String session) {
		try {
			UserStory userStory = new UserStory(uStoryId, description, 0);
			UserStory uStory = userStoryService.addUserStory(userStory,session);
			return new ResponseEntity<>(uStory, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteUserStory")
	public void deleteUserStory(@RequestParam String uStoryId) {
		try {
			userStoryService.deleteUserStory(uStoryId);
		} catch (Exception e) {
			
			logger.error(" Exception Occured during delete userstory  ",e.getMessage());
			throw new UserStoryNotFoundException(" Exception Occured during delete userstory  "+uStoryId);
		}
	}
	
	@GetMapping("/getUserStory")
	public UserStory getUserStory(@RequestParam String uStoryId) {
		try {
			UserStory userStory = userStoryService.getUserStory(uStoryId);
			
			if(userStory == null)
				throw new UserStoryNotFoundException(" UserStory not found for user story id: "+uStoryId);
			return userStory;
			
		} catch (Exception e) {
			
			logger.error(" Exception Occured during get userstory  ",e.getMessage());
			throw new UserStoryNotFoundException(" UserStory not found for user story id: "+uStoryId);
		}
	}
		
	@GetMapping("/getAllUserStories")
	public ResponseEntity<List<UserStory>> getAllUserStories() {
		try {
			List<UserStory> userStories = userStoryService.getAllUserStories();


			if (userStories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(userStories, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
