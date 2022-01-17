package com.planning.poker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.planning.poker.model.UserStory;

public interface UserStoryRepository extends JpaRepository<UserStory, String> {

	@Query(value = "SELECT us from UserStory us where us.uStoryId=?1")
	UserStory getUserStory(String uStoryId);


}
