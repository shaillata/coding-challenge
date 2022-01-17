package com.planning.poker.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.planning.poker.model.Member;
import com.planning.poker.model.MemberUserStory;
import com.planning.poker.model.UserStory;

public interface VoteService {

	List<Map<String, List<UserStory>>> getUserStoriesWithStatus(String session);

	int submitUserStoryVote(String uStoryId,String memberId,String session,Integer votePoint);

	List<Member> showMemberVotingStatus();

	List<UserStory> showUserStoryVotingStatus();

	UserStory moveUserStoryStatusToVoted(String uStoryId);

	List<MemberUserStory> stopuserStoryVoting(String uStoryId);

	int fetchVoteCountForUserStory(String uStoryId);

	int fetchVoteFinalResultForUserStory(String uStoryId,String memberId);

}
