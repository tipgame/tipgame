package com.tipgame.Threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.NullArgumentException;
import org.hibernate.Session;

import com.tipgame.CustomExceptions.CustomLoginException;
import com.tipgame.data.GameMatch;
import com.tipgame.data.Statistic;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;

public class RegistrationThread extends Thread {
	
	private DatabaseHelper databaseHelper;
	private User user;
	
	public RegistrationThread(User user)
	{
		this.databaseHelper = new DatabaseHelper();
		this.user = user;
	}
	@Override
	public void run()
	{
		Session hibernateSession = databaseHelper.getHibernateSession();
		try
		{		
			hibernateSession.beginTransaction();
			
			if (isUserAllowedToRegistrate(user))
			{
				hibernateSession.save(user);				
				hibernateSession.getTransaction().commit();
				
				CreateMatchUserConnections(user);			
				CreateNewStatisticForUser(user);
			}
		}
		catch (Exception e)
		{
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private Boolean isUserAllowedToRegistrate(User user) throws Exception
	{
		Session hibernateSession = databaseHelper.getHibernateSession();
		hibernateSession.beginTransaction();
		Boolean foundUser = false;
		List<String> l = hibernateSession.createSQLQuery("select email from AllowedUser").list();
		
		if (!l.contains(user.getEmail()))
		{
			throw new CustomLoginException("Sie besitzen keine Berechtigung sich anzumelden.");
		}
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
		Session hibernateSession = databaseHelper.getHibernateSession();
		try
		{
			Integer statisticId = null;
			
			hibernateSession.beginTransaction();
			databaseHelper.attachPojoToSession(hibernateSession, user);
			
			Statistic statistic = new Statistic();
			statistic.setUserId(user.getUserID());
			statisticId = (Integer) hibernateSession.save(statistic);
			
			user.setStatisticId(statisticId);
			
			hibernateSession.save(user);
			
			hibernateSession.getTransaction().commit();
		}
		catch (Exception e)
		{
			hibernateSession.getTransaction().rollback();
		}
	}
	
	private void SaveUserMatchConnection(List<UserMatchConnection> userMatchConnections)
	{
		Session hibernateSession = databaseHelper.getHibernateSession();
		try
		{
			hibernateSession.beginTransaction();
			
			for (int i = 0; i < userMatchConnections.size(); i++) {
				hibernateSession.save(userMatchConnections.get(i));
			}
			
			hibernateSession.getTransaction().commit();
		}
		catch (Exception e)
		{
			hibernateSession.getTransaction().rollback();
		}
	}

}
