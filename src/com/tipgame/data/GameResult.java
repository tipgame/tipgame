package com.tipgame.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GameResult {	
	private int resultId;
	private int resultHomeTeam;
	private int resultAwayTeam;
	
	@Id
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public int getResultHomeTeam() {
		return resultHomeTeam;
	}
	public void setResultHomeTeam(int resultHomeTeam) {
		this.resultHomeTeam = resultHomeTeam;
	}
	public int getResultAwayTeam() {
		return resultAwayTeam;
	}
	public void setResultAwayTeam(int resultAwayTeam) {
		this.resultAwayTeam = resultAwayTeam;
	}
	
	
}
