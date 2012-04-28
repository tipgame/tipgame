package com.tipgame.Threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tipgame.Administration.AdministrationView;
import com.tipgame.CustomExceptions.CustomLoginException;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.ui.Guide.GuideView;
import com.tipgame.ui.Home.HomeView;
import com.tipgame.ui.Statistics.StatisticView;
import com.tipgame.ui.Tipp.TippView;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.LoginForm.LoginEvent;

public class LoginThread extends Thread {
	
	private ProgressIndicator _progressIndicator;
	private LoginEvent _event;
	private TabSheet _mainTabSheet;
	private int _userId;
	private User _user;
	private Label _errorLabel;
	
	public LoginThread(ProgressIndicator progressIndicator, LoginEvent event,
			TabSheet tabsheet, Label errorLabel)
	{
		_errorLabel = errorLabel;
		_progressIndicator = progressIndicator;
		_event = event;
		_mainTabSheet = tabsheet;
	}
	@Override
    public void run (){
		try
		{
			_errorLabel.setValue("");
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
				_progressIndicator.setValue(new Float(1));
				_mainTabSheet.removeTab(_mainTabSheet.getTab(0));
			}	
			else
			{
				_progressIndicator.setHeight("0px");
				_progressIndicator.setCaption("");
				_errorLabel.setValue("Bei der Anmeldung ist ein Fehler aufgetreten. Sind alle Felder ausgefüllt?");
			}
		} catch (Exception e) {
			e.printStackTrace();	
			_errorLabel.setValue(e.getMessage());	
		}
			
    }
    
    private void computeStatistics()
	{
		StatisticThread statisticProcessor = new StatisticThread(_user);
		statisticProcessor.run();
	}
    
    private void setHiddenTabs() throws Exception
	{		
    	try
    	{
			HomeView homeView = new HomeView(_user);
			TippView TabTipp = new TippView(getMatchesForUserId(), _user);
			StatisticView reporting = new StatisticView();
			GuideView guideView = new GuideView();
	
			_mainTabSheet.addTab(homeView, "Übersicht", new ThemeResource("resources/icons/home.jpg"));
			_mainTabSheet.addTab(TabTipp, "Tipp", new ThemeResource("resources/icons/football.gif"));
			_mainTabSheet.addTab(reporting, "Auswertung", new ThemeResource("resources/icons/graph.png"));
			if (_user.getRights() == 65335)
			{
				AdministrationView adminView = new AdministrationView();
				_mainTabSheet.addTab(adminView, "Administration", new ThemeResource("resources/icons/admin.png"));
			}
			_mainTabSheet.addTab(guideView, "Anleitung", new ThemeResource("resources/icons/help.jpg"));
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		throw new Exception("Beim erstellen der Benutzeroberfläche kam es zu einem Fehler.");
    	}
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
	
	private boolean isLoginCorrect(LoginEvent event) throws Exception
	{
		User user = new User();
		try
		{
			DatabaseHelper databaseHelper = new DatabaseHelper();
			Session hibernateSession = databaseHelper.getHibernateSession();
			
			hibernateSession.beginTransaction();
			String sqlQuery = "FROM User WHERE username = :username AND password = :password";
			
			Query query = hibernateSession.createQuery(sqlQuery);		
			query.setString("username", event.getLoginParameter("username"));
			query.setString("password", TipgameUtils.byteArrayToHexString(TipgameUtils.computeHash(event.getLoginParameter("password"))));
			
			user = (User)query.uniqueResult();
			if(user != null)
			{
				_user = user;
				_userId =  user.getUserID();
			}
			hibernateSession.getTransaction().commit();
		}
		catch(Exception e) {
			throw new Exception("Bei der Anmeldung ist ein Fehler aufgetreten. Sind alle Felder ausgefüllt?");
		}
		return (user != null);
	}
}
