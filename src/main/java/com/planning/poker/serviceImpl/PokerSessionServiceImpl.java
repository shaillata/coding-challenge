package com.planning.poker.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planning.poker.constant.PlanningPokerConstant;
import com.planning.poker.controller.PlanningPokerVoteController;
import com.planning.poker.model.Member;
import com.planning.poker.model.PokerSession;
import com.planning.poker.model.UserStory;
import com.planning.poker.repository.MemberRepository;
import com.planning.poker.repository.PokerSessionRepository;
import com.planning.poker.service.PokerSessionService;

@Service
public class PokerSessionServiceImpl implements PokerSessionService {

	Logger logger = LoggerFactory.getLogger(PokerSessionServiceImpl.class);
	
	@Autowired
	PokerSessionRepository pokerSessionRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public PokerSession createPockerPlanningSession(PokerSession pokerSession,String memberName) {
		logger.info("createPockerPlanningSession start");
		PokerSession ps = null;
		//search with title in inmemory database
		//if exists then return the session; else create a new session
		if(pokerSession.getTitle()!=null && pokerSessionRepository.existsByTitle(pokerSession.getTitle())!=null) {
			
			ps = pokerSessionRepository.existsByTitle(pokerSession.getTitle());
		}else {
			Random random = new Random();
			int sessionVal = random.nextInt(10000, 20000);
			String session = String.valueOf(sessionVal);
			pokerSession.setSession(session);
			Member member = new Member(memberName,PlanningPokerConstant.NOT_VOTED);
			Set<Member> members = new HashSet<Member>();
			members.add(member);
			pokerSession.setMembers(members);
			//pokerSession.getMembers().add(member);
			pokerSession.getTitle();
			ps = pokerSessionRepository.save(pokerSession);
		}
		
		logger.info("createPockerPlanningSession end");
		return ps;
	}

	@Override
	public List<PokerSession> getAllPokerSession() {
			return pokerSessionRepository.findAll();
	}

	@Override
	public Optional<PokerSession> getPokerSessionBySession(String session) {
		return Optional.ofNullable(pokerSessionRepository.getPokerSessionBySession(session));
	}

	@Override
	public String getTitleOfTheSession(String session) {
		logger.info("getTitleOfTheSession start");
		PokerSession pokerSession = pokerSessionRepository.getById(session);
		logger.info("getTitleOfTheSession end");
		return pokerSession.getTitle();
	}

	@Override
	public List<Member> addUpdateMemberList(String session, String memberName) {
		logger.info("addUpdateMemberList start");
		PokerSession pokerSession = pokerSessionRepository.getById(session);
		Member member = new Member(memberName,PlanningPokerConstant.NOT_VOTED);
		member.setPokerSession(pokerSession);
		//add current member name to member list
		memberRepository.save(member);
		//get all member list
		Set<Member> memberSet = pokerSession.getMembers();
		List<Member> memberList = new ArrayList<Member>();
		memberList.addAll(memberSet);
		logger.info("addUpdateMemberList end");
		return memberList;
	}

	@Override
	public void destroyPokerSession(String session) {
		pokerSessionRepository.deleteById(session);
		
	}

	@Override
	public List<UserStory> getUserStoryListOfTheSession(String session) {
		logger.info("getUserStoryListOfTheSession start");
		PokerSession pokerSession = pokerSessionRepository.getById(session);
		Set<UserStory> userStories = pokerSession.getUserStories();
		List<UserStory> userStoryList = new ArrayList<UserStory>();
		userStoryList.addAll(userStories);
		logger.info("getUserStoryListOfTheSession end");
		return userStoryList;
	}

	@Override
	public List<Member> getMemberListOfTheSession(String session) {
		logger.info("getMemberListOfTheSession start");
		PokerSession pokerSession = pokerSessionRepository.getById(session);
		Set<Member> members = pokerSession.getMembers();
		List<Member> memberList = new ArrayList<Member>();
		memberList.addAll(members);
		logger.info("getMemberListOfTheSession end");
		return memberList;
	}

}
