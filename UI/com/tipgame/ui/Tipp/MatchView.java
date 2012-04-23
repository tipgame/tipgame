package com.tipgame.ui.Tipp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.tipgame.data.GameMatch;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.Reindeer;

public class MatchView extends CustomComponent {

	@AutoGenerated
	private GridLayout mainLayout;
	
	private DatabaseHelper _DatabaseHelper;
	
	@AutoGenerated
	private Button BtnSubmitTipp;
		
	private UserMatchConnection _UserMatchConnection;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = -6505119948672165582L;
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	public MatchView(UserMatchConnection userMatchConnection) {
		_UserMatchConnection = userMatchConnection;
		_DatabaseHelper = new DatabaseHelper();
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		GameMatch gameMatch = new GameMatch();
		gameMatch = getGameMatchFromId(userMatchConnection);
		
		Session hibernateSession = _DatabaseHelper.getHibernateSession();
		hibernateSession.beginTransaction();
		_DatabaseHelper.attachPojoToSession(hibernateSession, gameMatch);
		_DatabaseHelper.attachPojoToSession(hibernateSession, userMatchConnection);
		
		setKickOffLayout(gameMatch.getKickOff());
		
		setFixtureLayout(gameMatch.getHomeTeamName(), gameMatch.getHomeTeamImage(),
				gameMatch.getAwayTeamName(), gameMatch.getAwayTeamImage());
		
		setResultTippView(userMatchConnection.getResultTippHomeTeam(), userMatchConnection.getResultTippAwayTeam());
		
		setResultFinalView(gameMatch.getResultFinalHomeTeam(), gameMatch.getResultFinalAwayTeam());
		
		hibernateSession.getTransaction().commit();
		
	}
	
	private GameMatch getGameMatchFromId(UserMatchConnection match)
	{
		_DatabaseHelper = new DatabaseHelper();
		GameMatch gameMatch = new GameMatch();
		
		Session hibernateSession = _DatabaseHelper.getHibernateSession();
		hibernateSession.beginTransaction(); 
		_DatabaseHelper.attachPojoToSession(hibernateSession, match);

		Iterator iter = hibernateSession.createQuery(
			    "from GameMatch where gameMatchId = ?")
			    .setLong(0, match.getGameMatchId())
			    .iterate();
		
		while ( iter.hasNext() ) 
		{
			gameMatch = (GameMatch) iter.next();
		}
		
		hibernateSession.getTransaction().commit();
		
		return gameMatch;
	}
	
	private void setKickOffLayout(String kickOff)
	{
		KickOffView kickOffView = new KickOffView(kickOff);
		mainLayout.addComponent(kickOffView, 0, 0);
	}
	
	private void setFixtureLayout(String homeTeamName, String homeTeamImage, 
			String awayTeamName, String awayTeamImage)
	{
		FixtureView fixtureView = new FixtureView(homeTeamName, homeTeamImage, 
				awayTeamName, awayTeamImage);
		mainLayout.addComponent(fixtureView, 1, 0);
	}
	
	private void setResultTippView(String resultTippHomeTeam, String resultTippAwayTeam)
	{
		ResultView resultView = new ResultView(resultTippHomeTeam, resultTippAwayTeam, true);
		mainLayout.addComponent(resultView, 3, 0);
	}
	
	private void setResultFinalView(String resultFinalHomeTeam, String resultFinalAwayTeam)
	{
		ResultView resultView = new ResultView(resultFinalHomeTeam,	resultFinalAwayTeam, false);
		mainLayout.addComponent(resultView, 2, 0);
	}
	
	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("60px");
		mainLayout.setMargin(false);
		mainLayout.setColumns(6);
		
		// BtnSubmitTipp
		BtnSubmitTipp = new Button("",this,"onSubmitTippButtonClick");
		BtnSubmitTipp.setCaption("Tipp abgeben");
		BtnSubmitTipp.setImmediate(false);
		BtnSubmitTipp.setWidth("-1px");
		BtnSubmitTipp.setHeight("-1px");
		BtnSubmitTipp.setStyleName(Reindeer.BUTTON_LINK);
		mainLayout.addComponent(BtnSubmitTipp, 5, 0);
		mainLayout.setComponentAlignment(BtnSubmitTipp, new Alignment(48));
		 		
		
		return mainLayout;
	}
	
	public void onSubmitTippButtonClick(Button.ClickEvent event)
	{
		Session session = _DatabaseHelper.getHibernateSession();
		session.beginTransaction();
		_DatabaseHelper.attachPojoToSession(session, _UserMatchConnection);
		ResultView view = (ResultView)mainLayout.getComponent(3, 0);		
		_UserMatchConnection.setResultTippHomeTeam(view.getTippHomeTeam());
		_UserMatchConnection.setResultTippAwayTeam(view.getTippAwayTeam());
		session.save(_UserMatchConnection);
		session.getTransaction().commit();
	}

}