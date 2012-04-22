package com.tipgame.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.processor.StatisticProcessor;
import com.tipgame.ui.GuideView;
import com.tipgame.ui.HomeView;
import com.tipgame.ui.StatisticView;
import com.tipgame.ui.TippView;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TabSheet;

public class CustomLoginListener implements LoginForm.LoginListener
{
	private static final long serialVersionUID = -5685166950324371044L;
	private TabSheet _mainTabSheet;
	private ProgressIndicator _progressIndicator;
	private int _userId;
	private User _user;

	public void onLogin(LoginEvent event) {
		_progressIndicator.setHeight("50px");
		_progressIndicator.setValue(new Float(1/10));
		if (isLoginCorrect(event))
		{
			_progressIndicator.setValue(new Float(4/10));
			computeStatistics();
			_mainTabSheet.removeAllComponents();
			setHiddenTabs();
			_progressIndicator.setValue(new Float(8/10));
		}
		_progressIndicator.setValue(new Float(10/10));
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


	public void SetMainTabSheet(TabSheet mainTabSheet) {
		this._mainTabSheet = mainTabSheet;	
	}

	public void setProgressIndicator(ProgressIndicator _progressIndicator) {
		this._progressIndicator = _progressIndicator;
	}
	
}

