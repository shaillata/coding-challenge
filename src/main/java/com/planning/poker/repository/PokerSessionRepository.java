package com.planning.poker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.planning.poker.model.PokerSession;

public interface PokerSessionRepository extends JpaRepository<PokerSession, String> {

	@Query(value = "SELECT ps from PokerSession ps where ps.title=?1")
	PokerSession existsByTitle(String title);

	@Query(value = "SELECT ps from PokerSession ps where ps.session=?1")
	PokerSession getPokerSessionBySession(String session);


}
