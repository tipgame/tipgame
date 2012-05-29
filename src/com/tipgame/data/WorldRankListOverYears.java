package com.tipgame.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class WorldRankListOverYears {

	private int id;
	private String name;
	private int average;
	private int position;
	private int year_1998;
	private int year_2000;
	private int year_2002;
	private int year_2004;
	private int year_2006;
	private int year_2008;
	private int year_2010;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getYear_1998() {
		return year_1998;
	}
	public void setYear_1998(int year_1998) {
		this.year_1998 = year_1998;
	}
	public int getYear_2000() {
		return year_2000;
	}
	public void setYear_2000(int year_2000) {
		this.year_2000 = year_2000;
	}
	public int getYear_2002() {
		return year_2002;
	}
	public void setYear_2002(int year_2002) {
		this.year_2002 = year_2002;
	}
	public int getYear_2004() {
		return year_2004;
	}
	public void setYear_2004(int year_2004) {
		this.year_2004 = year_2004;
	}
	public int getYear_2006() {
		return year_2006;
	}
	public void setYear_2006(int year_2006) {
		this.year_2006 = year_2006;
	}
	public int getYear_2008() {
		return year_2008;
	}
	public void setYear_2008(int year_2008) {
		this.year_2008 = year_2008;
	}
	public int getYear_2010() {
		return year_2010;
	}
	public void setYear_2010(int year_2010) {
		this.year_2010 = year_2010;
	}
	
}
