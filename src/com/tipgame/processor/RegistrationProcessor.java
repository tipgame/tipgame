package com.tipgame.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.tipgame.data.GameMatch;
import com.tipgame.data.Statistic;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;

public class RegistrationProcessor {	
	
	private DatabaseHelper databaseHelper;
	
	public RegistrationProcessor()
	{
		databaseHelper = new DatabaseHelper();
	}
	
	public Boolean CreateRegistration(User user)
	{		
		Session hibernateSession = databaseHelper.getHibernateSession();
		
		hibernateSession.beginTransaction();
		
		hibernateSession.save(user);
		
		hibernateSession.getTransaction().commit();
		
		CreateMatchUserConnections(user);
		CreateNewStatisticForUser(user);
		return true;
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
