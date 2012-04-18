package com.tipgame.data;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KickOff {
	private int kickOffId;
	private Timestamp kickOff;
	
	@Id
	public int getKickOffId() {
		return kickOffId;
	}
	public void setKickOffId(int kickOffId) {
		this.kickOffId = kickOffId;
	}
	public Timestamp getKickOff() {
		return kickOff;
	}
	public void setKickOff(Timestamp kickOff) {
		this.kickOff = kickOff;
	}
}
