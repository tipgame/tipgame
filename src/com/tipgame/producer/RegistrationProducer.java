package com.tipgame.producer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.tipgame.data.GameMatch;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;

public class RegistrationProducer {	
	
	private DatabaseHelper databaseHelper;
	
	public RegistrationProducer()
	{
		databaseHelper = new DatabaseHelper();
	}
	
	public Boolean CreateRegistration(User user)
	{		
		Session hibernateSession = databaseHelper.GetHibernateSession();
		
		hibernateSession.beginTransaction();
		
		hibernateSession.save(user);
		
		hibernateSession.getTransaction().commit();
		
		CreateMatchUserConnections(user);
		return true;
	}
	
	private void CreateMatchUserConnections(User user)
	{
		List<UserMatchConnection> userMatchConnections = new ArrayList<UserMatchConnection>();
		Session hibernateSession = databaseHelper.GetHibernateSession();
		hibernateSession.beginTransaction();
		// fetch ids
		Iterator iter = hibernateSession.createQuery("from GameMatch").iterate();
		while ( iter.hasNext() ) 
		{
		    GameMatch match = (GameMatch) iter.next();
		    UserMatchConnection userMatchConnection = new UserMatchConnection();
		    userMatchConnection.setGameMatchId(match.getGameMatchId());
		    userMatchConnection.setUserId(user.getUserID());
		    
		    userMatchConnections.add(userMatchConnection);
		}
		
		hibernateSession.getTransaction().commit();
		
		SaveUserMatchConnection(userMatchConnections);
	}
	
	private void SaveUserMatchConnection(List<UserMatchConnection> userMatchConnections)
	{
		Session hibernateSession = databaseHelper.GetHibernateSession();
		hibernateSession.beginTransaction();
		
		for (int i = 0; i < userMatchConnections.size(); i++) {
			hibernateSession.save(userMatchConnections.get(i));
		}
		
		hibernateSession.getTransaction().commit();
	}
}
