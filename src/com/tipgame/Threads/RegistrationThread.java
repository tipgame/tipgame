package com.tipgame.Threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.NullArgumentException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.tipgame.CustomExceptions.CustomLoginException;
import com.tipgame.data.GameMatch;
import com.tipgame.data.Statistic;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.utils.TipgameUtils;

public class RegistrationThread extends Thread {
	
	private DatabaseHelper databaseHelper;
	private User user;
	private String registrationCode;
	
	public RegistrationThread(User user, String registrationCode)
	{
		this.databaseHelper = DatabaseHelper.getInstance();
		this.user = user;
		this.registrationCode = registrationCode;
	}
	@Override
	public void run()
	{
		Session hibernateSession = databaseHelper.getHibernateSession();
		try
		{		
			hibernateSession.beginTransaction();
			
			if (isUserAllowedToRegistrate(registrationCode))
			{
				doesUserAlreadyExists(user);
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
	
	private void doesUserAlreadyExists(User user) throws Exception
	{
		Session hibernateSession = databaseHelper.getHibernateSession();
		hibernateSession.beginTransaction();
		String sql = "FROM User WHERE username = :username";
		String username = new String(user.getUsername().getBytes("ISO-8859-1"), "UTF-8");

		Query query = hibernateSession.createQuery(sql);		
		query.setString("username", username);
		try {
			user = (User)query.uniqueResult();
			if(user != null)
				throw new Exception("Dieser Benutzername existiert bereits.");
		}
		catch(Exception e) {
			throw new Exception("Dieser Benutzername existiert bereits.");
		}
	}
	
	private Boolean isUserAllowedToRegistrate(String registrationCode) throws Exception
	{
		Session hibernateSession = databaseHelper.getHibernateSession();
		hibernateSession.beginTransaction();
		List<String> l = hibernateSession.createSQLQuery("select registrationCode from AllowedUser").list();
		
		if (!l.contains(registrationCode))
		{
			l = null;
			throw new CustomLoginException("Sie besitzen keine \n Berechtigung sich anzumelden.");
		}
		return true;
	}
	
	private void CreateMatchUserConnections(User user)
	{
		List<UserMatchConnection> userMatchConnections = new ArrayList<UserMatchConnection>();
		Session hibernateSession = databaseHelper.getHibernateSession();
		try {
			hibernateSession.beginTransaction();
			List<GameMatch> list = hibernateSession.createCriteria(GameMatch.class)
					.addOrder(Order.asc("group")).addOrder(Order.asc("kickOff")).list();
			
			for (GameMatch gameMatch : list) {
				UserMatchConnection userMatchConnection = new UserMatchConnection();
			    userMatchConnection.setGameMatchId(gameMatch.getGameMatchId());
			    userMatchConnection.setUserId(user.getUserID());
			    userMatchConnection.setResultTippAwayTeam("");
			    userMatchConnection.setResultTippHomeTeam("");
			    userMatchConnection.setAlreadyProcessed(false);
			    userMatchConnection.setRound(gameMatch.getRound());
			    userMatchConnections.add(userMatchConnection);
			}
			
			hibernateSession.getTransaction().commit();
			
			SaveUserMatchConnection(userMatchConnections);
		} catch (Exception e) {
			hibernateSession.getTransaction().rollback();
		}
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
