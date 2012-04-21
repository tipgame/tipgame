package com.tipgame.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tipgame.data.GameMatch;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.ui.HomeView;
import com.tipgame.ui.ReportingView;
import com.tipgame.ui.TippView;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.TabSheet;

public class CustomLoginListener implements LoginForm.LoginListener
{
	private static final long serialVersionUID = -5685166950324371044L;
	private TabSheet _mainTabSheet;
	private int _userId;

	public void onLogin(LoginEvent event) {
		if (isLoginCorrect(event))
		{
			_mainTabSheet.removeAllComponents();
			setHiddenTabs();
		}		
	}
	
	private void setHiddenTabs()
	{
		TippView TabTipp = new TippView(getMatchesForUserId());
		ReportingView reporting = new ReportingView();
		HomeView homeView = new HomeView();
		
		_mainTabSheet.addTab(homeView, "Übersicht", new ThemeResource("resources/icons/home.jpg"));
		_mainTabSheet.addTab(TabTipp, "Tipp", new ThemeResource("resources/icons/football.png"));
		_mainTabSheet.addTab(reporting, "Auswertung", new ThemeResource("resources/icons/graph.png"));
		
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
			_userId =  user.getUserID();
		}
		hibernateSession.getTransaction().commit();
		return (user != null);
	}


	public void SetMainTabSheet(TabSheet mainTabSheet) {
		this._mainTabSheet = mainTabSheet;	
	}
	
}

