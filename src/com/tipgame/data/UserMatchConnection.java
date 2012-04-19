package com.tipgame.data;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserMatchConnection {
	
	private Integer id;
	private Integer gameMatchId;
	private Integer userId;
	private String resultTippHomeTeam;
	private String resultTippAwayTeam;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(nullable=true)
	public Integer getGameMatchId() {
		return gameMatchId;
	}
	public void setGameMatchId(Integer integer) {
		this.gameMatchId = integer;
	}
	
	@Column(nullable=true)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getResultTippHomeTeam() {
		return resultTippHomeTeam;
	}
	public void setResultTippHomeTeam(String resultTippHomeTeam) {
		this.resultTippHomeTeam = resultTippHomeTeam;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getResultTippAwayTeam() {
		return resultTippAwayTeam;
	}
	public void setResultTippAwayTeam(String resultTippAwayTeam) {
		this.resultTippAwayTeam = resultTippAwayTeam;
	}
}
