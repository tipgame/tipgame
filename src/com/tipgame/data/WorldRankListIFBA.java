package com.tipgame.data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class WorldRankListIFBA {
	private int id;
	private String rank;
	private String name;
	private String points;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
}
