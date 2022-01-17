package com.planning.poker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
import com.planning.poker.repository.MemberRepository;
import com.planning.poker.repository.PokerSessionRepository;
import com.planning.poker.serviceImpl.PokerSessionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class PokerSessionServiceTests {

	@InjectMocks
	public PokerSessionServiceImpl pokerSessionServiceMocked;

	@Mock
	PokerSessionRepository pokerSessionRepository;

	@Mock
	MemberRepository memberRepository;
	
	 static List<PokerSession> pokerSessions = new ArrayList<PokerSession>();
	 static PokerSession pokerSession = null;
	 static Member member2 = null;
	
	

	//	@Ignore
	@Test
	//@Order(1)
	void createPockerPlanningSessionTest() {

		Mockito.when(pokerSessionRepository.save(pokerSession)).thenReturn(pokerSession);
		PokerSession ps = pokerSessionServiceMocked.createPockerPlanningSession(pokerSession, "XYZ");
		assertEquals(pokerSession, ps);
	}

	@Test
	//@Order(2)
	void getAllPokerSessionTest() {

		Mockito.when(pokerSessionRepository.findAll()).thenReturn(pokerSessions);
		List<PokerSession> pokerSessionList = pokerSessionServiceMocked.getAllPokerSession();
		assertEquals(pokerSessions, pokerSessionList);
	}
	
	@Test
	//@Order(3)
	void getPokerSessionBySessionTest() {

		String session = "12345";
		Mockito.when(pokerSessionRepository.getPokerSessionBySession(session)).thenReturn(pokerSession);
		Optional<PokerSession> ps = pokerSessionServiceMocked.getPokerSessionBySession(session);
		assertEquals(pokerSession, ps.get());
	}
	
	@Test
	//@Order(4)
	void getTitleOfTheSessionTest() {

		String session = "12345";
		//String title = ""
		Mockito.when(pokerSessionRepository.getById(session)).thenReturn(pokerSession);
		String title = pokerSessionServiceMocked.getTitleOfTheSession(session);
		assertEquals(pokerSession.getTitle(), title);
	}
	
	@Test
	void addUpdateMemberListTest() {

		String session = "12345";
		String memberName = "ABC";
		Mockito.when(pokerSessionRepository.getById(session)).thenReturn(pokerSession);
		Mockito.when(memberRepository.save(member2)).thenReturn(member2);
		List<Member> memberList = pokerSessionServiceMocked.addUpdateMemberList(session, memberName);
		System.out.println(memberList.size());
		//assertEquals(pokerSession.getTitle(), memberList);
	}
	
	@Test
	void getUserStoryListOfTheSessionTest() {

		String session = "12345";
		List<UserStory> userStoryListExpected = new ArrayList<UserStory>();
		//String title = ""
		Mockito.when(pokerSessionRepository.getById(session)).thenReturn(pokerSession);
		userStoryListExpected.addAll(pokerSession.getUserStories());
		List<UserStory> userStoryList = pokerSessionServiceMocked.getUserStoryListOfTheSession(session);
		assertEquals(userStoryListExpected, userStoryList);
	}
	
	@Test
	void getMemberListOfTheSessionTest() {

		String session = "12345";
		List<Member> memberListExpected = new ArrayList<Member>();
		//String title = ""
		Mockito.when(pokerSessionRepository.getById(session)).thenReturn(pokerSession);
		memberListExpected.addAll(pokerSession.getMembers());
		List<Member> memberList = pokerSessionServiceMocked.getMemberListOfTheSession(session);
		assertEquals(memberListExpected, memberList);
	}
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		pokerSession = createPokerSession();
		pokerSessions = createPokerSessionList(pokerSession);
		member2 = createMember();
	}

	private Member createMember() {
		member2 = new Member("ABC", PlanningPokerConstant.NOT_VOTED);
		member2.setMemberId("2");
		pokerSession.getMembers().add(member2);
		member2.setPokerSession(pokerSession);
		return member2;
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
