package com.planning.poker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planning.poker.model.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
