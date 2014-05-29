package com.tipgame.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameMatch {
	
	private Integer gameMatchId;
	private String kickOff;
	private String homeTeamImage;
	private String homeTeamName;
	private String awayTeamImage;
	private String awayTeamName;
	private String resultFinalHomeTeam;
	private String resultFinalAwayTeam;
	private String group;
	private String round;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getGameMatchId() {
		return gameMatchId;
	}
	public void setGameMatchId(Integer gameMatchId) {
		this.gameMatchId = gameMatchId;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getKickOff() {
		return kickOff;
	}
	public void setKickOff(String kickOff) {
		this.kickOff = kickOff;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getHomeTeamImage() {
		return homeTeamImage;
	}
	public void setHomeTeamImage(String homeTeamImage) {
		this.homeTeamImage = homeTeamImage;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getHomeTeamName() {
		return homeTeamName;
	}
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getAwayTeamImage() {
		return awayTeamImage;
	}
	public void setAwayTeamImage(String awayTeamImage) {
		this.awayTeamImage = awayTeamImage;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getAwayTeamName() {
		return awayTeamName;
	}
	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getResultFinalHomeTeam() {
		return resultFinalHomeTeam;
	}
	public void setResultFinalHomeTeam(String resultFinalHomeTeam) {
		this.resultFinalHomeTeam = resultFinalHomeTeam;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getResultFinalAwayTeam() {
		return resultFinalAwayTeam;
	}
	public void setResultFinalAwayTeam(String resultFinalAwayTeam) {
		this.resultFinalAwayTeam = resultFinalAwayTeam;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
}
