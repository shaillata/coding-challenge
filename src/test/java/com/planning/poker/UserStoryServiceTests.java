package com.planning.poker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.planning.poker.constant.PlanningPokerConstant;
import com.planning.poker.model.Member;
import com.planning.poker.model.PokerSession;
import com.planning.poker.model.UserStory;
import com.planning.poker.model.VotingStatus;
import com.planning.poker.repository.PokerSessionRepository;
import com.planning.poker.repository.UserStoryRepository;
import com.planning.poker.serviceImpl.PokerSessionServiceImpl;
import com.planning.poker.serviceImpl.UserStoryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class UserStoryServiceTests {

	@InjectMocks
	public UserStoryServiceImpl userStoryServiceMocked;

	@Mock
	PokerSessionRepository pokerSessionRepository;

	@Mock
	UserStoryRepository userStoryRepository;
	
	 static List<PokerSession> pokerSessions = new ArrayList<PokerSession>();
	 static PokerSession pokerSession = null;
	 static Member member2 = null;
	 static UserStory userStory2 = null;
	
	

	@Test
	void addUserStoryTest() {

		String session = "12345";
		Mockito.when(pokerSessionRepository.getById(session)).thenReturn(pokerSession);
		Mockito.when(userStoryRepository.save(userStory2)).thenReturn(userStory2);
		UserStory us = userStoryServiceMocked.addUserStory(userStory2, session);
		assertEquals(userStory2, us);
	}

	@Test
	void getUserStoryTest() {

		String uStoryId = "us2";
		Mockito.when(userStoryRepository.getUserStory(uStoryId)).thenReturn(userStory2);
		UserStory us = userStoryServiceMocked.getUserStory(uStoryId);
		assertEquals(userStory2, us);
	}
	
	@Test
	void deleteUserStoryTest() {

		String uStoryId = "us2";
		Mockito.when(userStoryRepository.getById(uStoryId)).thenReturn(userStory2);
		userStoryServiceMocked.deleteUserStory(uStoryId);
		System.out.println("done!!!");
		//assertEquals(userStory2, us);
	}
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		pokerSession = createPokerSession();
		pokerSessions = createPokerSessionList(pokerSession);
		userStory2 = createUserStory();
		//member2 = createMember();
	}

	
	
	private UserStory createUserStory() {
		userStory2 = new UserStory("us2", "desc2", 0);
		userStory2.setVotingStatus(VotingStatus.PENDING);
		pokerSession.getUserStories().add(userStory2);
		userStory2.setPokerSession(pokerSession);
		return userStory2;
	}



	public static List<PokerSession> createPokerSessionList(PokerSession pokerSession) {
		pokerSessions = new ArrayList<PokerSession>();
		
		
		List<PokerSession> pokerSessions = new ArrayList<PokerSession>();
		pokerSessions.add(pokerSession);
		return pokerSessions;
	}

	private PokerSession createPokerSession() {
		UserStory userStory = new UserStory("us1", "desc1", 0);
		userStory.setVotingStatus(VotingStatus.PENDING);
		Set<UserStory> userStories = new HashSet<UserStory>();
		userStories.add(userStory);
		Member member = new Member("XYZ", PlanningPokerConstant.NOT_VOTED);
		member.setMemberId("1");
		Set<Member> members = new HashSet<Member>();
		members.add(member);
		pokerSession = new PokerSession("12345", "title1", "deck1", userStories, members);
		return pokerSession;
	}
}
