package com.tipgame.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {
	
	private int teamID;
	private String imageFlag;
	private String teamName;
	
	@Id
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public String getImageFlag() {
		return imageFlag;
	}
	public void setImageFlag(String imageFlag) {
		this.imageFlag = imageFlag;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
