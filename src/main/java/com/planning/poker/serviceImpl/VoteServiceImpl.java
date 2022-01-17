package com.planning.poker.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planning.poker.constant.PlanningPokerConstant;
import com.planning.poker.exception.VoteNotAcceptedException;
import com.planning.poker.model.Member;
import com.planning.poker.model.MemberUserStory;
import com.planning.poker.model.UserStory;
import com.planning.poker.model.VotingStatus;
import com.planning.poker.repository.MemberRepository;
import com.planning.poker.repository.MemberUserStoryRepository;
import com.planning.poker.repository.UserStoryRepository;
import com.planning.poker.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

	Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);
	
	
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	UserStoryRepository userStoryRepository;
	
	
	@Autowired
	MemberUserStoryRepository memberUserStoryRepository;
	
	@Override
	public List<Map<String, List<UserStory>>> getUserStoriesWithStatus(String session) {

		//fetch all vote status
		//List<VoteStatus> voteStatuses = voteStatusRepository.findAll();
		//fetch userstory against each status
		//voteStatuses.stream().collect(Collectors.groupingBy(VoteStatus::getStatusId));
		Map<String, List<UserStory>> map = null;
		List<UserStory> userStories = null;
		/*
		 * for(VoteStatus voteStatus:voteStatuses) { if(map.get(voteStatus.getStatus())
		 * == null) { userStories = new ArrayList<UserStory>(); map = new
		 * HashMap<String, List<UserStory>>(); } map.put(voteStatus.getStatus(),
		 * userStories); }
		 */
		
		
		return null;
	}

	@Override
	public int submitUserStoryVote(String uStoryId,String memberId,String session,Integer votePoint) {
		logger.info("submitUserStoryVote start");
//		int count = 0;

		//get userstory by uStoryId
		UserStory uStory = userStoryRepository.getById(uStoryId);
		//get count of user story
				int count = uStory.getCount();
		Member member = memberRepository.getById(memberId);		
		String memberStatus = member.getMemberStatus();
		//if userStory Status is VOTED then throw exception with message No More Vote Accepted
		if(uStory.getVotingStatus()!=null && uStory.getVotingStatus().compareTo(VotingStatus.VOTED)==0) {
			throw new VoteNotAcceptedException("Voting is not Available");
		}else if(uStory.getVotingStatus()!=null && uStory.getVotingStatus().compareTo(VotingStatus.PENDING)==0){
			//change the status to VOTING
			uStory.setVotingStatus(VotingStatus.VOTING);
		}
		
		
		//increment count by 1
		count = count+1;
		uStory.setCount(count);
		//update member status to VOTED from NOT_VOTED
		if(memberStatus != null && memberStatus.equalsIgnoreCase(PlanningPokerConstant.NOT_VOTED)) {
			member.setMemberStatus(PlanningPokerConstant.VOTED);
		}
		/*
		 * member.setVotePoint(votePoint); uStory.getMembers().add(member);
		 */
		MemberUserStory memberUstory = new MemberUserStory();
		memberUstory.setMember(member);
		memberUstory.setUserStory(uStory);
		memberUstory.setVotePoint(votePoint);
		
		
		//update userstory with status (if applicable) and count
		//userStoryRepository.save(uStory);
		//userStoryRepository.flush();
		memberUserStoryRepository.save(memberUstory);
		logger.info("submitUserStoryVote end");
		return count;
	}

	@Override
	public List<Member> showMemberVotingStatus() {
		//get all members with status
		logger.info("showMemberVotingStatus start");
		List<Member> members = memberRepository.findAll();
		return members;
	}

	@Override
	public List<UserStory> showUserStoryVotingStatus() {
		// get all userStories
		logger.info("showUserStoryVotingStatus start");
		return userStoryRepository.findAll();
	}

	@Override
	public UserStory moveUserStoryStatusToVoted(String uStoryId) {
		logger.info("moveUserStoryStatusToVoted start");
		// update userstory status to VOTED
		//VoteStatus voteStatus = voteStatusRepository.findByStatusDesc(PlanningPokerConstant.VOTED);
		UserStory userStory = userStoryRepository.getById(uStoryId); 
		userStory.setVotingStatus(VotingStatus.VOTED);
		userStoryRepository.save(userStory);
		//fetch member votedetails , for this need Many to one mapping for member and userstory
		
		//Set<Member> members = userStory.getMembers();
		
		//show vote count against storyid
		int voteCount = userStory.getCount();
		logger.info("moveUserStoryStatusToVoted end");
		return userStory;
	}

	@Override
	public List<MemberUserStory> stopuserStoryVoting(String uStoryId) {
		logger.info("stopuserStoryVoting start");
		// update userstory status to VOTED
		UserStory userStory = userStoryRepository.getById(uStoryId); 
		userStory.setVotingStatus(VotingStatus.VOTED);
		userStoryRepository.save(userStory);
		//fetch MemberUserStory against a uStoryId
		List<MemberUserStory> memberUserStoryList = memberUserStoryRepository.findByUserStoryId(uStoryId);
		logger.info("stopuserStoryVoting end");
		return memberUserStoryList;
	}

	@Override
	public int fetchVoteCountForUserStory(String uStoryId) {
		logger.info("fectVoteCountForUserStory start");
		UserStory userStory = userStoryRepository.getById(uStoryId); 
		
		int voteCount = userStory.getCount();
		logger.info("fectVoteCountForUserStory end",voteCount);
		return voteCount;
	}

	@Override
	public int fetchVoteFinalResultForUserStory(String uStoryId,String memberId) {
		logger.info("fectVoteFinalResultForUserStory start");
		
		
		UserStory userStory = userStoryRepository.getById(uStoryId);
		
		Set<MemberUserStory> memberUserStories = userStory.getMemberUstory();
		List<MemberUserStory> memberUserStoryList = new ArrayList<MemberUserStory>();
		memberUserStoryList.addAll(memberUserStories);
		
		Optional<MemberUserStory> memberUStory = memberUserStoryList.stream()
				.sorted(Comparator.comparingInt(MemberUserStory::getVotePoint).reversed()).findFirst()
				 ;
		int voteFinalResult = memberUStory.get().getVotePoint();
		logger.info("fectVoteFinalResultForUserStory end",voteFinalResult);
		return voteFinalResult;
	}

}
