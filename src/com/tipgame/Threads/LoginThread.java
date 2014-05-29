package com.tipgame.Threads;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import com.tipgame.Administration.AdministrationView;
import com.tipgame.data.User;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.listener.TabChangeListener;
import com.tipgame.ui.Guide.GuideView;
import com.tipgame.ui.Home.HomeView;
import com.tipgame.ui.Statistics.StatisticView;
import com.tipgame.ui.Tipp.TippView;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.LoginForm.LoginEvent;

public class LoginThread extends Thread {
	
	private ProgressIndicator _progressIndicator;
	private LoginEvent _event;
	private TabSheet _mainTabSheet;
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
			_progressIndicator.setHeight("0px");
			_progressIndicator.setCaption("");
		}
			
    }
    
    private void computeStatistics()
	{
    	Session session = DatabaseHelper.getInstance().getHibernateSession();
		try {
			session.beginTransaction();
			Iterator<User> iter = session.createQuery(
				    "from User")
				    .iterate();
			
			while(iter.hasNext()) {
				User user = iter.next();
				StatisticThread statisticProcessor = new StatisticThread(user);
				statisticProcessor.setDoFullComputation(true);
				statisticProcessor.run();
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

    private void setHiddenTabs() throws Exception
	{		
    	try
    	{
			HomeView homeView = new HomeView(_user);
			
			TippView tabTipp = new TippView(_user, _mainTabSheet.getWindow());
			
			StatisticView reporting = new StatisticView();
			GuideView guideView = new GuideView();
	
			_mainTabSheet.addTab(homeView, "Übersicht", new ThemeResource("resources/icons/home.jpg"));
			_mainTabSheet.addTab(tabTipp, "Tipp", new ThemeResource("resources/icons/football.gif"));
			_mainTabSheet.addTab(reporting, "Auswertung", new ThemeResource("resources/icons/graph.png"));
			_mainTabSheet.addTab(guideView, "Anleitung", new ThemeResource("resources/icons/help.jpg"));
			AbsoluteLayout absLayout = new AbsoluteLayout();
			absLayout.setCaption("Logout");
			_mainTabSheet.addTab(absLayout, "Logout", new ThemeResource("resources/icons/logout.png"));
			_mainTabSheet.addListener(new TabChangeListener());

    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		throw new Exception("Beim erstellen der Benutzeroberfläche kam es zu einem Fehler.");
    	}
	}
	
	private boolean isLoginCorrect(LoginEvent event) throws Exception
	{
		User user = new User();
		try
		{
			DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
			Session hibernateSession = databaseHelper.getHibernateSession();
			
			hibernateSession.beginTransaction();
			String sqlQuery = "FROM User WHERE username = :username AND password = :password";
			
			String username = new String(event.getLoginParameter("username").getBytes("ISO-8859-1"), "UTF-8");
			String password = new String(event.getLoginParameter("password").getBytes("ISO-8859-1"), "UTF-8");
			Query query = hibernateSession.createQuery(sqlQuery);		
			query.setString("username", username);
			query.setString("password", TipgameUtils.byteArrayToHexString(TipgameUtils.computeHash(password)));
			
			user = (User)query.uniqueResult();
			if(user != null)
			{
				_user = user;
				user.getUserID();
			}
			else
			{
				throw new Exception("Der Benutzer konnte nicht gefunden werden. Überprüfen Sie bitte Kennwort und Benutzername.");
			}
			hibernateSession.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return (user != null);
	}
}
