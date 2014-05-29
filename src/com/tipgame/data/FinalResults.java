package com.tipgame.data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class FinalResults {
	private int id;
	private String winner;
	private String resultGermany;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT ''")
	public String getResultGermany() {
		return resultGermany;
	}
	public void setResultGermany(String resultGermany) {
		this.resultGermany = resultGermany;
	}
	
	
}
