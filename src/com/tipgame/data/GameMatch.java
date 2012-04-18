package com.tipgame.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameMatch {
	
	private int gameMatchId;
	private String kickOff;
	private String homeTeamImage;
	private String homeTeamName;
	private String awayTeamImage;
	private String awayTeamName;
	private int resultFinalHomeTeam;
	private int resultFinalAwayTeam;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getGameMatchId() {
		return gameMatchId;
	}
	public void setGameMatchId(int gameMatchId) {
		this.gameMatchId = gameMatchId;
	}
	
	@Column(nullable=true)
	public String getKickOff() {
		return kickOff;
	}
	public void setKickOff(String kickOff) {
		this.kickOff = kickOff;
	}
	
	@Column(nullable=true)
	public String getHomeTeamImage() {
		return homeTeamImage;
	}
	public void setHomeTeamImage(String homeTeamImage) {
		this.homeTeamImage = homeTeamImage;
	}
	
	@Column(nullable=true)
	public String getHomeTeamName() {
		return homeTeamName;
	}
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}
	
	@Column(nullable=true)
	public String getAwayTeamImage() {
		return awayTeamImage;
	}
	public void setAwayTeamImage(String awayTeamImage) {
		this.awayTeamImage = awayTeamImage;
	}
	
	@Column(nullable=true)
	public String getAwayTeamName() {
		return awayTeamName;
	}
	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}
	
	@Column(nullable=true)
	public int getResultFinalHomeTeam() {
		return resultFinalHomeTeam;
	}
	public void setResultFinalHomeTeam(int resultFinalHomeTeam) {
		this.resultFinalHomeTeam = resultFinalHomeTeam;
	}
	
	@Column(nullable=true)
	public int getResultFinalAwayTeam() {
		return resultFinalAwayTeam;
	}
	public void setResultFinalAwayTeam(int resultFinalAwayTeam) {
		this.resultFinalAwayTeam = resultFinalAwayTeam;
	}
}
