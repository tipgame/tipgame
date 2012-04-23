package com.tipgame.Threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.tipgame.data.GameMatch;
import com.tipgame.data.Statistic;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.vaadin.ui.ProgressIndicator;

public class RegistrationThread extends Thread {
	
	private DatabaseHelper databaseHelper;
	private ProgressIndicator progressIndicator;
	private User user;
	
	public RegistrationThread(ProgressIndicator progressIndicator, User user)
	{
		this.databaseHelper = new DatabaseHelper();
		this.progressIndicator = progressIndicator;
		this.user = user;
	}
	
	public void run()
	{
		progressIndicator.setHeight("50px");
		progressIndicator.setValue(new Float(0.2));
		progressIndicator.setCaption("Erzeuge Benutzer ...");

		Session hibernateSession = databaseHelper.getHibernateSession();		
		hibernateSession.beginTransaction();
		if (isUserAllowedToRegistrate(user))
			hibernateSession.save(user);
		else
			throw new RuntimeException("");
		
		hibernateSession.getTransaction().commit();
		
		progressIndicator.setValue(new Float(0.4));
		progressIndicator.setCaption("Erzeuge Tipps ...");
		CreateMatchUserConnections(user);
		progressIndicator.setValue(new Float(0.8));
		progressIndicator.setCaption("Erzeuge Statistiken ...");
		CreateNewStatisticForUser(user);
	}
	
	private Boolean isUserAllowedToRegistrate(User user)
	{
		return false;
	}
	
	private void CreateMatchUserConnections(User user)
	{
		List<UserMatchConnection> userMatchConnections = new ArrayList<UserMatchConnection>();
		Session hibernateSession = databaseHelper.getHibernateSession();
		hibernateSession.beginTransaction();
		// fetch ids
		Iterator iter = hibernateSession.createQuery("from GameMatch").iterate();
		while ( iter.hasNext() ) 
		{
		    GameMatch match = (GameMatch) iter.next();
		    UserMatchConnection userMatchConnection = new UserMatchConnection();
		    userMatchConnection.setGameMatchId(match.getGameMatchId());
		    userMatchConnection.setUserId(user.getUserID());
		    userMatchConnection.setResultTippAwayTeam("");
		    userMatchConnection.setResultTippHomeTeam("");
		    userMatchConnection.setAlreadyProcessed(false);
		    userMatchConnections.add(userMatchConnection);
		}
		
		hibernateSession.getTransaction().commit();
		
		SaveUserMatchConnection(userMatchConnections);
	}
	
	private void CreateNewStatisticForUser(User user)
	{
		Integer statisticId = null;
		Session hibernateSession = databaseHelper.getHibernateSession();
		hibernateSession.beginTransaction();
		databaseHelper.attachPojoToSession(hibernateSession, user);
		
		Statistic statistic = new Statistic();
		statistic.setUserId(user.getUserID());
		statisticId = (Integer) hibernateSession.save(statistic);
		
		user.setStatisticId(statisticId);
		
		hibernateSession.save(user);
		
		hibernateSession.getTransaction().commit();
	}
	
	private void SaveUserMatchConnection(List<UserMatchConnection> userMatchConnections)
	{
		Session hibernateSession = databaseHelper.getHibernateSession();
		hibernateSession.beginTransaction();
		
		for (int i = 0; i < userMatchConnections.size(); i++) {
			hibernateSession.save(userMatchConnections.get(i));
		}
		
		hibernateSession.getTransaction().commit();
	}

}
