package com.planning.poker.service;

import java.util.List;
import java.util.Optional;

import com.planning.poker.model.Member;
import com.planning.poker.model.PokerSession;
import com.planning.poker.model.UserStory;

public interface PokerSessionService {

	PokerSession createPockerPlanningSession(PokerSession pockerSession,String memberName);

	List<PokerSession> getAllPokerSession();

	Optional<PokerSession> getPokerSessionBySession(String session);

	String getTitleOfTheSession(String session);

	List<Member> addUpdateMemberList(String session, String memberName);

	void destroyPokerSession(String session);

	List<UserStory> getUserStoryListOfTheSession(String session);

	List<Member> getMemberListOfTheSession(String session);


}
