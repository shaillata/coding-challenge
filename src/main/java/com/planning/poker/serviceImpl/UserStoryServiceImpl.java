package com.planning.poker.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planning.poker.exception.UserStoryDeleteNotAvailableException;
import com.planning.poker.model.PokerSession;
import com.planning.poker.model.UserStory;
import com.planning.poker.model.VotingStatus;
import com.planning.poker.repository.PokerSessionRepository;
import com.planning.poker.repository.UserStoryRepository;
import com.planning.poker.service.UserStoryService;

@Service
public class UserStoryServiceImpl implements UserStoryService {

	Logger logger = LoggerFactory.getLogger(UserStoryServiceImpl.class);
	
	@Autowired
	UserStoryRepository userStoryRepository;
	
	
	
	@Autowired
	PokerSessionRepository pokerSessionRepository;
	
	public UserStory addUserStory(UserStory userStory,String session) {
		logger.info("addUserStory start");
		PokerSession pokerSession = pokerSessionRepository.getById(session);
		//VoteStatus voteStatus = voteStatusRepository.findByStatusDesc(PlanningPokerConstant.PENDING);
		userStory.setCount(0);
		userStory.setVotingStatus(VotingStatus.PENDING);
		userStory.setPokerSession(pokerSession);
		//userStory.setVoteStatus(voteStatus);
		//voteStatus.setUserStory(userStory);
		logger.info("addUserStory end");
		return userStoryRepository.save(userStory);
	}

	@Override
	public void deleteUserStory(String uStoryId) {
		logger.info("deleteUserStory start");
		//check if status is pending then delete
		UserStory uStory = userStoryRepository.getById(uStoryId);
	if(uStory.getVotingStatus() != null && 
			uStory.getVotingStatus().compareTo(VotingStatus.PENDING)==0) {
			userStoryRepository.deleteById(uStoryId);
		}else {
			//throw exception with message can not delete 
			throw new UserStoryDeleteNotAvailableException("Delete Option not available for user story :"+uStoryId);
		}
		
	logger.info("deleteUserStory end");
		
	}

	@Override
	public List<UserStory> getAllUserStories() {
		logger.info("getAllUserStories start");
		List<UserStory> userStories = userStoryRepository.findAll();
		logger.info("getAllUserStories end");
		return userStories;
	}

	@Override
	public UserStory getUserStory(String uStoryId) {
		logger.info("getUserStory start");
		UserStory uStory = userStoryRepository.getUserStory(uStoryId);
		logger.info("getUserStory end");
		return uStory;
	}
	
	
	
}
