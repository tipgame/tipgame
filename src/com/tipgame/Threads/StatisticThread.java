package com.tipgame.Threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.tipgame.data.FinalResults;
import com.tipgame.data.GameMatch;
import com.tipgame.data.Statistic;
import com.tipgame.data.Team;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.processor.PointProcessor;
import com.tipgame.utils.TipgameUtils;

public class StatisticThread extends Thread{
	
	private User user;
	private DatabaseHelper databaseHelper;
	private HashMap<Integer, GameMatch> gameMatchs;
	private ArrayList<UserMatchConnection> processedUserMatchConnections;
	private Boolean doFullComputation;
	
	public StatisticThread()
	{
		databaseHelper = DatabaseHelper.getInstance();
		gameMatchs = new HashMap<Integer,GameMatch>();
		processedUserMatchConnections = new ArrayList<UserMatchConnection>(); 
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void run()
	{
		startCalculation();
	}
	
	public void startCalculation()
	{
		Boolean somethingToDo = false;
		Integer points = 0;
		String matchIDs = getGameMatchIdForFinishedGames();
		Session session = databaseHelper.getHibernateSession();
		int userId = 0;
		try {
			databaseHelper.attachPojoToSession(session, user);
			
			userId = user.getUserID();
			if (matchIDs != "")
			{
				Iterator<UserMatchConnection> iter = session.createQuery(
					    "from UserMatchConnection where userId = ? and resultTippHomeTeam != '' and "+
						" resultTippAwayTeam != '' and alreadyProcessed=0 and gameMatchId in ("+matchIDs+")")
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
				savePoints(points);
				computeRank();
				updateUserMatchConnections();
			}		
			
			CalculateFullPointsAfterLastMatch();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}
	}
	
	public void CalculateTeamPointsAndRankForAllTeams()
	{
		Session session = databaseHelper.getHibernateSession();	
		Iterator<Team> iter = session.createQuery(
			    "from Team")
				.iterate();
		
		while(iter.hasNext())
		{	
			Team team = iter.next();

			String[] userIDs = team.getUserIds().split(";");
			float sumOfPoints = 0;
			for (String user : userIDs) {
				sumOfPoints = sumOfPoints + getAllPointsForUser(Integer.parseInt(user));
			}
			
			if(userIDs.length > 0)
			{
				float teamPoints = ((sumOfPoints / userIDs.length) * 5);
				databaseHelper.attachPojoToSession(session, team);
				team.setPoints(teamPoints);		
				session.saveOrUpdate(team);
			}
		}
		computeTeamRank();
	}
	
	private void computeTeamRank(){
		Session session = databaseHelper.getHibernateSession();

		String sqlQuery = "FROM Team order by points desc";
		Iterator<Team> iter = session.createQuery(sqlQuery).iterate();
		int rank = 1;
		float points = 0;
		while(iter.hasNext())
		{
			Team team = iter.next();
			if(team.getPoints() < points)
				rank++;						
			team.setRank(rank);			
			session.saveOrUpdate(team);
			
			points = team.getPoints();
		}
	}
	private int getAllPointsForUser(int userId) 
	{
		Session session = databaseHelper.getHibernateSession();	
		Iterator<Statistic> iter = session.createQuery(
			    "from Statistic where userId = ?")
			    .setLong(0, userId)
			    .iterate();
		
		while(iter.hasNext())
		{
			Statistic statistic = iter.next();
			return statistic.getPoints();
		}
		return 0;
	}
	
	private void updateUserMatchConnections()
	{
		Session session = databaseHelper.getHibernateSession();		
		for (UserMatchConnection userMatchConnection : processedUserMatchConnections) {
			userMatchConnection.setAlreadyProcessed(true);
			session.saveOrUpdate(userMatchConnection);
		}
	}
	
	private void savePoints(Integer points)
	{
		Session session = databaseHelper.getHibernateSession();
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
			session.saveOrUpdate(statistic);			
		}
	}
	
	private void computeRank()
	{
		Session session = databaseHelper.getHibernateSession();

		String sqlQuery = "FROM Statistic order by points desc";
		Iterator<Statistic> iter = session.createQuery(sqlQuery).iterate();
		int rank = 1;
		int points = 0;
		while(iter.hasNext())
		{
			Statistic statistic = iter.next();
			if(statistic.getPoints() < points)
				rank++;						
			statistic.setRank(rank);			
			session.saveOrUpdate(statistic);
			
			points = statistic.getPoints();
		}
	}
	
	private Integer getPointsFromUserStatistic()
	{
		Integer pointsFromUserStatistic = 0;
		Session session = databaseHelper.getHibernateSession();
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
		databaseHelper.attachPojoToSession(session, user);
		
		statisticId = user.getStatisticId();
	
		return statisticId;
	}
	
	private Integer computePoints(UserMatchConnection userMatchConnection)
	{
		Session session = databaseHelper.getHibernateSession();
		databaseHelper.attachPojoToSession(session, userMatchConnection);
		GameMatch match = gameMatchs.get(userMatchConnection.getGameMatchId());
		databaseHelper.attachPojoToSession(session, match);
		Integer homeTeamFinal = Integer.valueOf(match.getResultFinalHomeTeam());
		Integer awayTeamFinal = Integer.valueOf(match.getResultFinalAwayTeam());
		
		Integer homeTeamTipp = Integer.valueOf(userMatchConnection.getResultTippHomeTeam().trim());
		Integer awayTeamTipp = Integer.valueOf(userMatchConnection.getResultTippAwayTeam().trim());
		
		PointProcessor pointProcessor = new PointProcessor(homeTeamFinal, awayTeamFinal,
				homeTeamTipp, awayTeamTipp);
		
		return pointProcessor.computePoints();
	}
	
	private String getGameMatchIdForFinishedGames()
	{
		String matchIDs = "";
		Session session = databaseHelper.getHibernateSession();
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
		
		return matchIDs;
	}
	
	private void CalculateFullPointsAfterLastMatch()
	{
		Integer points = 0;		
		if(TipgameUtils.compareDates("10.07.2016 00:01")) {
			Session session = databaseHelper.getHibernateSession();
			databaseHelper.attachPojoToSession(session, user);
			String winner = user.getWinnertipp();
			String tippGermany = user.getGermanytipp();
			
			Iterator<FinalResults> iter = session.createQuery(
				    "from FinalResults")
				    .iterate();
			while(iter.hasNext()) {
				FinalResults finalResult = iter.next();
				if (finalResult.getResultGermany().equalsIgnoreCase(tippGermany)){
					points = points + 10;
				}
				if (finalResult.getWinner().equalsIgnoreCase(winner)) {
					points = points + 10;
				}
			}
			savePoints(points);
		}	
	}

	public Boolean getDoFullComputation() {
		return doFullComputation;
	}

	public void setDoFullComputation(Boolean doFullComputation) {
		this.doFullComputation = doFullComputation;
	}
}
