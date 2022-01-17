package com.planning.poker;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.planning.poker.controller.PlanningPokerSessionController;
import com.planning.poker.service.PokerSessionService;

//@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(PlanningPokerSessionController.class)
class PlanningPokerSessionControllerTests {

	@Autowired 
    private MockMvc mvc;
	
	 @MockBean 
	  private PokerSessionService pokerSessionServiceMocked; 
	
	
	 @Ignore
	 @Test
		void getAllPokerSessionTest() {
		 
		 
		 
		}
	 
	@Ignore
	 @Test
	void contextLoads() {
	}

}
