package com.tipgame.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Match {
	
	private int matchId;
	private int homeTeamId;
	private int awayTeamId;
	private int kickOffId;
	private int resultTippId;
	private int resultFinalId;
	private int userId;
	
	@Id
	public int getMatchId() {
		return matchId;
	}	
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public int getHomeTeamId() {
		return homeTeamId;
	}
	public void setHomeTeamId(int homeTeamId) {
		this.homeTeamId = homeTeamId;
	}
	public int getAwayTeamId() {
		return awayTeamId;
	}
	public void setAwayTeamId(int awayTeamId) {
		this.awayTeamId = awayTeamId;
	}
	public int getKickOffId() {
		return kickOffId;
	}
	public void setKickOffId(int kickOffId) {
		this.kickOffId = kickOffId;
	}
	public int getResultTippId() {
		return resultTippId;
	}
	public void setResultTippId(int resultTippId) {
		this.resultTippId = resultTippId;
	}
	public int getResultFinalId() {
		return resultFinalId;
	}
	public void setResultFinalId(int resultFinalId) {
		this.resultFinalId = resultFinalId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
