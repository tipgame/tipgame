package com.tipgame.ui.Tipp;

import java.util.Iterator;

import org.hibernate.Session;

import com.tipgame.Threads.StatisticThread;
import com.tipgame.data.GameMatch;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.themes.Reindeer;

public class MatchView extends CustomComponent {

	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private GridLayout gridLayout_2;
	@AutoGenerated
	private Button BtnSubmitTipp;
	@AutoGenerated
	private Embedded okIcon;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = -6505119948672165582L;
	private DatabaseHelper _DatabaseHelper;
	
	private UserMatchConnection _UserMatchConnection;
	private User user;
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	public MatchView(UserMatchConnection userMatchConnection, User user) {
		this.user = user;
		_UserMatchConnection = userMatchConnection;
		_DatabaseHelper = DatabaseHelper.getInstance();
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
		
		setResultTippView(userMatchConnection.getResultTippHomeTeam(), 
				userMatchConnection.getResultTippAwayTeam(), gameMatch.getKickOff());
		
		setResultFinalView(gameMatch.getResultFinalHomeTeam(), gameMatch.getResultFinalAwayTeam());
		
		hibernateSession.getTransaction().commit();
		
	}
	
	private GameMatch getGameMatchFromId(UserMatchConnection match)
	{
		_DatabaseHelper = DatabaseHelper.getInstance();
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
	
	private void setResultTippView(String resultTippHomeTeam, String resultTippAwayTeam, String kickOff)
	{
		if((!resultTippHomeTeam.isEmpty()) && (!resultTippAwayTeam.isEmpty()))
		{
			okIcon.setVisible(true);
		}
		ResultView resultView = new ResultView(resultTippHomeTeam, resultTippAwayTeam, true, kickOff, true);
		mainLayout.addComponent(resultView, 3, 0);
		mainLayout.setComponentAlignment(resultView, new Alignment(48));
	}
	
	private void setResultFinalView(String resultFinalHomeTeam, String resultFinalAwayTeam)
	{
		ResultView resultView = new ResultView(resultFinalHomeTeam,	resultFinalAwayTeam, false, "", false);
		mainLayout.addComponent(resultView, 2, 0);
	}
	
	public void onSubmitTippButtonClick(Button.ClickEvent event)
	{
		if(fieldsAreFilled())
		{
			okIcon.setVisible(false);
			Session session = _DatabaseHelper.getHibernateSession();
			session.beginTransaction();
			_DatabaseHelper.attachPojoToSession(session, _UserMatchConnection);
			ResultView view = (ResultView)mainLayout.getComponent(3, 0);		
			_UserMatchConnection.setResultTippHomeTeam(view.getResultHomeTeam());
			_UserMatchConnection.setResultTippAwayTeam(view.getResultAwayTeam());
			session.saveOrUpdate(_UserMatchConnection);
			
			session.getTransaction().commit();
			StatisticThread statistic = new StatisticThread(user);
			statistic.start();
			okIcon.setVisible(true);
		}
		else
		{
			getWindow().showNotification("Bitte f�llen Sie alle Tippfelder f�r die gew�hlte Partie aus!");
		}
	}
	
	private Boolean fieldsAreFilled()
	{
		ResultView view = (ResultView)mainLayout.getComponent(3, 0);
		return (view.getResultHomeTeam() != "") && (view.getResultAwayTeam() != "");
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
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("60px");
		
		// gridLayout_2
		gridLayout_2 = buildGridLayout_2();
		mainLayout.addComponent(gridLayout_2, 5, 0);
		mainLayout.setComponentAlignment(gridLayout_2, new Alignment(48));
		
		return mainLayout;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_2() {
		// common part: create layout
		gridLayout_2 = new GridLayout();
		gridLayout_2.setImmediate(false);
		gridLayout_2.setWidth("-1px");
		gridLayout_2.setHeight("-1px");
		gridLayout_2.setMargin(false);
		gridLayout_2.setColumns(2);
		
		// okIcon
		okIcon = new Embedded();
		okIcon.setVisible(false);
		okIcon.setImmediate(false);
		okIcon.setWidth("15px");
		okIcon.setHeight("15px");
		okIcon.setSource(new ThemeResource("resources/icons/ok.png"));
		okIcon.setType(1);
		okIcon.setMimeType("image/png");
		gridLayout_2.addComponent(okIcon, 0, 0);
		gridLayout_2.setComponentAlignment(okIcon, new Alignment(48));
		
		// BtnSubmitTipp
		BtnSubmitTipp = new Button("",this,"onSubmitTippButtonClick");
		BtnSubmitTipp.setCaption("Tipp abgeben");
		BtnSubmitTipp.setImmediate(false);
		BtnSubmitTipp.setWidth("-1px");
		BtnSubmitTipp.setHeight("-1px");
		BtnSubmitTipp.setStyleName(Reindeer.BUTTON_LINK);
		gridLayout_2.addComponent(BtnSubmitTipp, 1, 0);
		gridLayout_2.setComponentAlignment(BtnSubmitTipp, new Alignment(48));
		
		return gridLayout_2;
	}

}
