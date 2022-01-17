package com.planning.poker.service;

import java.util.List;

import com.planning.poker.model.UserStory;

public interface UserStoryService {
	public UserStory addUserStory(UserStory userStory,String session);
	public void deleteUserStory(String uStoryId);
	public List<UserStory> getAllUserStories();
	public UserStory getUserStory(String uStoryId);
	
}
