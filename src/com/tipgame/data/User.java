package com.tipgame.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	private int userID;
	private String name;
	private String christianname;
	private String password;
	private String username;
	private int rights;
	private Integer statisticId;
	private String email;
	private String winnertipp;
	private String germanytipp;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChristianname() {
		return christianname;
	}
	public void setChristianname(String christianname) {
		this.christianname = christianname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}
	public Integer getStatisticId() {
		return statisticId;
	}
	public void setStatisticId(Integer statisticId) {
		this.statisticId = statisticId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWinnertipp() {
		return winnertipp;
	}
	public void setWinnertipp(String winnertipp) {
		this.winnertipp = winnertipp;
	}
	public String getGermanytipp() {
		return germanytipp;
	}
	public void setGermanytipp(String germanytipp) {
		this.germanytipp = germanytipp;
	}	
}
