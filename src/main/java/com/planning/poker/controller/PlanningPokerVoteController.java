package com.planning.poker.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planning.poker.exception.VotePointOutOfRangeException;
import com.planning.poker.model.Member;
import com.planning.poker.model.MemberUserStory;
import com.planning.poker.model.UserStory;
import com.planning.poker.service.VoteService;

@RestController
@RequestMapping("/planningPoker/voting")
public class PlanningPokerVoteController {

	Logger logger = LoggerFactory.getLogger(PlanningPokerVoteController.class);
	
	@Autowired
	VoteService voteService;
	
	@GetMapping("/getUserStoriesWithStatus")
	public List<Map<String, List<UserStory>>> getUserStoriesWithStatus(@RequestParam String session) {
		List<Map<String, List<UserStory>>> listStoriesWithStatus = voteService.getUserStoriesWithStatus(session);

			return listStoriesWithStatus;
	}
	
	@PostMapping("/submitUserStoryVote")
	public int submitUserStoryVote(@RequestParam String uStoryId,@RequestParam String memberId,@RequestParam String session,@RequestParam Integer votePoint) {

		if(votePoint<=0 || votePoint>9) {
			throw new VotePointOutOfRangeException("Vote Ponit is not in range");
		}
		
		return voteService.submitUserStoryVote(uStoryId,memberId,session,votePoint);
	}
	
	@GetMapping("/showMemberVotingStatus")
	public List<Member> showMemberVotingStatus() {

			return voteService.showMemberVotingStatus();
	}
	
	@GetMapping("/showUserStoryVotingStatus")
	public List<UserStory> showUserStoryVotingStatus() {

			return voteService.showUserStoryVotingStatus();
	}
	
	@PostMapping("/moveStoryStatusToVoted")
	public UserStory moveUserStoryStatusToVoted(@RequestParam String uStoryId) {

		return voteService.moveUserStoryStatusToVoted(uStoryId);
	}
	@PostMapping("/stopuserStoryVoting")
	public List<MemberUserStory> stopuserStoryVoting(@RequestParam String uStoryId) {

		return voteService.stopuserStoryVoting(uStoryId);
	}
	
	@GetMapping("/fetchVoteCountForUserStory")
	public int fetchVoteCountForUserStory(@RequestParam String uStoryId) {

			return voteService.fetchVoteCountForUserStory(uStoryId);
	}
	
	@GetMapping("/fetchVoteFinalResultForUserStory")
	public int fetchVoteFinalResultForUserStory(@RequestParam String uStoryId,@RequestParam String memberId) {

			return voteService.fetchVoteFinalResultForUserStory(uStoryId,memberId);
	}
}
