package com.tipgame.Threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.processor.StatisticProcessor;
import com.tipgame.ui.Guide.GuideView;
import com.tipgame.ui.Home.HomeView;
import com.tipgame.ui.Statistics.StatisticView;
import com.tipgame.ui.Tipp.TippView;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.LoginForm.LoginEvent;

public class LoginThread extends Thread {
	
	private ProgressIndicator _progressIndicator;
	private LoginEvent _event;
	private TabSheet _mainTabSheet;
	private int _userId;
	private User _user;
	
	public LoginThread(ProgressIndicator progressIndicator, LoginEvent event,
			TabSheet tabsheet)
	{
		_progressIndicator = progressIndicator;
		_event = event;
		_mainTabSheet = tabsheet;
	}
	
    public void run () {
    	_progressIndicator.setHeight("50px");
		_progressIndicator.setValue(new Float(0.1));
		_progressIndicator.setCaption("Einloggen ...");
		if (isLoginCorrect(_event))
		{
			_progressIndicator.setValue(new Float(0.3));
			_progressIndicator.setCaption("Berechne Statistiken ...");
			computeStatistics();
			_progressIndicator.setValue(new Float(0.6));
			_progressIndicator.setCaption("Erstelle Benutzeroberfläche ...");
			setHiddenTabs();
		}
		_progressIndicator.setValue(new Float(1));
		_mainTabSheet.removeTab(_mainTabSheet.getTab(0));
    }
    
    private void computeStatistics()
	{
		StatisticProcessor statisticProcessor = new StatisticProcessor(_user);
		statisticProcessor.computeStatisticForUser();
	}
    
    private void setHiddenTabs()
	{
		TippView TabTipp = new TippView(getMatchesForUserId());
		StatisticView reporting = new StatisticView();
		HomeView homeView = new HomeView(_user);
		GuideView guideView = new GuideView();

		_mainTabSheet.addTab(homeView, "Übersicht", new ThemeResource("resources/icons/home.jpg"));
		_mainTabSheet.addTab(TabTipp, "Tipp", new ThemeResource("resources/icons/football.gif"));
		_mainTabSheet.addTab(reporting, "Auswertung", new ThemeResource("resources/icons/graph.png"));
		_mainTabSheet.addTab(guideView, "Anleitung", new ThemeResource("resources/icons/help.jpg"));
		
	}
	
	private List<UserMatchConnection> getMatchesForUserId()
	{
		List<UserMatchConnection> matches = new ArrayList<UserMatchConnection>();
		DatabaseHelper databaseHelper = new DatabaseHelper();
		Session hibernateSession = databaseHelper.getHibernateSession();
		hibernateSession.beginTransaction();
		// fetch ids
		
		Iterator iter = hibernateSession.createQuery(
			    "from UserMatchConnection where userId = ?")
			    .setLong(0, _userId)
			    .iterate();
		
		while ( iter.hasNext() ) 
		{
			matches.add((UserMatchConnection) iter.next());
		}
		
		hibernateSession.getTransaction().commit();
		
		return matches;
	}
	
	private boolean isLoginCorrect(LoginEvent event)
	{
		DatabaseHelper databaseHelper = new DatabaseHelper();
		Session hibernateSession = databaseHelper.getHibernateSession();
		
		hibernateSession.beginTransaction();
		String sqlQuery = "FROM User WHERE username = :username AND password = :password";
		
		Query query = hibernateSession.createQuery(sqlQuery);		
		try {
			query.setString("username", event.getLoginParameter("username"));
			query.setString("password", TipgameUtils.byteArrayToHexString(TipgameUtils.computeHash(event.getLoginParameter("password"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user = new User();
		user = (User)query.uniqueResult();
		if(user != null)
		{
			_user = user;
			_userId =  user.getUserID();
		}
		hibernateSession.getTransaction().commit();
		return (user != null);
	}
}
