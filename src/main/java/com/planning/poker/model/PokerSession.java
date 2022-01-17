package com.planning.poker.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
@Entity
@Table(name = "PokerSession")
public class PokerSession {

	@Id
	@Column(name = "session")
	private String session;

	@Column(name = "title")
	private String title;

	@Column(name = "deckType")
	private String deckType;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "session")
	private Set<UserStory> userStories;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "session")
	private Set<Member> members;

	public PokerSession() {
		super();
	}

	public PokerSession(String title, String deckType) {
		super();
		this.title = title;
		this.deckType = deckType;
	}

	public PokerSession(String session, String title, String deckType, Set<UserStory> userStories,
			Set<Member> members) {
		super();
		this.session = session;
		this.title = title;
		this.deckType = deckType;
		this.userStories = userStories;
		this.members = members;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDeckType() {
		return deckType;
	}

	public void setDeckType(String deckType) {
		this.deckType = deckType;
	}

	public Set<UserStory> getUserStories() {
		return userStories;
	}

	public void setUserStories(Set<UserStory> userStories) {
		this.userStories = userStories;
	}

	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

}
