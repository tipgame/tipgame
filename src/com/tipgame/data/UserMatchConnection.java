package com.tipgame.data;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserMatchConnection {
	
	private int id;
	private int gameMatchId;
	private int userId;
	private int resultTippHomeTeam;
	private int resultTippAwayTeam;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(nullable=true)
	public int getGameMatchId() {
		return gameMatchId;
	}
	public void setGameMatchId(int gameMatchId) {
		this.gameMatchId = gameMatchId;
	}
	
	@Column(nullable=true)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(nullable=true)
	public int getResultTippHomeTeam() {
		return resultTippHomeTeam;
	}
	public void setResultTippHomeTeam(int resultTippHomeTeam) {
		this.resultTippHomeTeam = resultTippHomeTeam;
	}
	
	@Column(nullable=true)
	public int getResultTippAwayTeam() {
		return resultTippAwayTeam;
	}
	public void setResultTippAwayTeam(int resultTippAwayTeam) {
		this.resultTippAwayTeam = resultTippAwayTeam;
	}
}
