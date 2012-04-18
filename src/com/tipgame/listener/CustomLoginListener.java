package com.tipgame.listener;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tipgame.data.Match;
import com.tipgame.data.User;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.ui.TippView;
import com.tipgame.utils.TipgameUtils;
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
		_mainTabSheet.addTab(TabTipp, "Tipp");
	}
	
	private List<Match> getMatchesForUserId()
	{
		List<Match> foo = new ArrayList<Match>();
		Match match = new Match();
		match.setAwayTeamId(1);
		match.setHomeTeamId(1);
		match.setKickOffId(1);
		match.setResultFinalId(1);
		match.setResultTippId(1);
		match.setUserId(1);
		foo.add(match);
		
		match = new Match();
		match.setAwayTeamId(2);
		match.setHomeTeamId(2);
		match.setKickOffId(2);
		match.setResultFinalId(2);
		match.setResultTippId(2);
		match.setUserId(1);
		foo.add(match);
		return foo;
	}
	
	private boolean isLoginCorrect(LoginEvent event)
	{
		DatabaseHelper databaseHelper = new DatabaseHelper();
		Session hibernateSession = databaseHelper.GetHibernateSession();
		
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

