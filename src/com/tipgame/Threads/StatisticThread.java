package com.tipgame.Threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tipgame.data.GameMatch;
import com.tipgame.data.Statistic;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.processor.PointProcessor;

public class StatisticThread extends Thread{
	
	private User user;
	private DatabaseHelper databaseHelper;
	private HashMap<Integer, GameMatch> gameMatchs;
	private ArrayList<UserMatchConnection> processedUserMatchConnections;
	
	public StatisticThread(User user)
	{
		databaseHelper = new DatabaseHelper();
		gameMatchs = new HashMap<Integer,GameMatch>();
		processedUserMatchConnections = new ArrayList<UserMatchConnection>(); 
		this.user = user;
	}
	
	@Override
	public void run()
	{
		Boolean somethingToDo = false;
		Integer points = 0;
		String matchIDs = getGameMatchIdForFinishedGames();
		Session session = databaseHelper.getHibernateSession();
		try {
			session.beginTransaction();
			databaseHelper.attachPojoToSession(session, user);
			
			int userId = user.getUserID();
			if (matchIDs != "")
			{
				Iterator<UserMatchConnection> iter = session.createQuery(
					    "from UserMatchConnection where userId = ? and resultTippHomeTeam != '' and "+
						" resultTippAwayTeam != '' and alreadyProcessed = 0 and gameMatchId in ("+matchIDs+")")
					    .setLong(0, userId)
					    .iterate();
				while(iter.hasNext())
				{
					somethingToDo = true;
					UserMatchConnection userMatchConnection = iter.next();
					points = points+computePoints(userMatchConnection);
					processedUserMatchConnections.add(userMatchConnection);
				}
			}
			
			if(somethingToDo)
			{
				Integer rank = computeRank(points);
				saveAll(points, rank);
				updateUserMatchConnections();
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	private void updateUserMatchConnections()
	{
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();
		
		for (UserMatchConnection userMatchConnection : processedUserMatchConnections) {
			userMatchConnection.setAlreadyProcessed(true);
			session.saveOrUpdate(userMatchConnection);
		}
	}
	
	private void saveAll(Integer points, Integer rank)
	{
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();
		databaseHelper.attachPojoToSession(session, user);
		int userId = user.getUserID();
		Integer pointsSum = points + getPointsFromUserStatistic();
		Iterator<Statistic> iter = session.createQuery(
			    "from Statistic where userId = ?")
			    .setLong(0, userId)
			    .iterate();
		while(iter.hasNext())
		{
			Statistic statistic = iter.next();
			statistic.setPoints(pointsSum);
			statistic.setRank(rank);
			
			session.saveOrUpdate(statistic);			
		}
	}
	
	private Integer computeRank(Integer points)
	{
		Integer rank = getNumberOfUsers();
		points = points + getPointsFromUserStatistic();
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();

		String sqlQuery = "FROM Statistic order by points asc";
		Iterator<Statistic> iter = session.createQuery(sqlQuery).iterate();
		while(iter.hasNext())
		{
			Statistic statistic = iter.next();
			
			if(statistic.getUserId() != user.getUserID()) {
				if ((statistic.getPoints() < points) && (rank > 1))
					rank--;
				else
					break;	
			}
		}
		return rank;
	}
	
	private Integer getNumberOfUsers()
	{
		Integer numberOfUsers = 0;
		Session session = databaseHelper.getHibernateSession();
		try {
			session.beginTransaction();
	
			String sqlQuery = "FROM User";
			Iterator<Statistic> iter = session.createQuery(sqlQuery).iterate();
			while(iter.hasNext())
			{
				iter.next();
				numberOfUsers++;
				
			} 
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return numberOfUsers;
	}
	
	private Integer getPointsFromUserStatistic()
	{
		Integer pointsFromUserStatistic = 0;
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();
		
		String sqlQuery = "SELECT points FROM Statistic WHERE id = "+String.valueOf(getStatisticId());
		Query query = session.createQuery(sqlQuery);
		List<Integer> list = query.list();
		for (int i = 0; i < list.size(); i++) {
			pointsFromUserStatistic = list.get(i);
		}
		
		return pointsFromUserStatistic;
	}
	
	private Integer getStatisticId()
	{
		Integer statisticId = null;
		
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();
		databaseHelper.attachPojoToSession(session, user);
		
		statisticId = user.getStatisticId();
	
		return statisticId;
	}
	
	private Integer computePoints(UserMatchConnection userMatchConnection)
	{
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();
		databaseHelper.attachPojoToSession(session, userMatchConnection);
		GameMatch match = gameMatchs.get(userMatchConnection.getGameMatchId());
		databaseHelper.attachPojoToSession(session, match);
		Integer homeTeamFinal = Integer.valueOf(match.getResultFinalHomeTeam());
		Integer awayTeamFinal = Integer.valueOf(match.getResultFinalAwayTeam());
		
		Integer homeTeamTipp = Integer.valueOf(userMatchConnection.getResultTippHomeTeam());
		Integer awayTeamTipp = Integer.valueOf(userMatchConnection.getResultTippAwayTeam());
		
		PointProcessor pointProcessor = new PointProcessor(homeTeamFinal, awayTeamFinal,
				homeTeamTipp, awayTeamTipp);
		
		return pointProcessor.computePoints();
	}
	
	private String getGameMatchIdForFinishedGames()
	{
		String matchIDs = "";
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();
		
		Iterator<GameMatch> iter = session.createQuery(
			    "from GameMatch where resultFinalHomeTeam != '' and "+
				" resultFinalAwayTeam != ''")
			    .iterate();
		while(iter.hasNext())
		{
			GameMatch match = iter.next();
			if (iter.hasNext())
			{
				matchIDs = matchIDs+String.valueOf(match.getGameMatchId())+",";
			}
			else
			{
				matchIDs = matchIDs+String.valueOf(match.getGameMatchId());
			}
			gameMatchs.put(match.getGameMatchId(), match);
		}
		
		//session.getTransaction().commit();
		
		return matchIDs;
	}

}
