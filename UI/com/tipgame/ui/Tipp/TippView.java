package com.tipgame.ui.Tipp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import com.tipgame.data.GameMatch;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Runo;

public class TippView extends CustomComponent {

	private static final long serialVersionUID = -3251458515243143780L;
	
	@AutoGenerated
	private VerticalLayout mainLayout;
	private VerticalLayout preliminaryTippLayout;
	private VerticalLayout koTippLayout;
	private User user;
	private TabSheet tippTab;
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

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	public TippView(User user, Window appWindow) {
		
		this.user = user;		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		createMatchView( "Vorrunde");
		createMatchView( "Achtelfinale");
		createMatchView( "Viertelfinale");
		createMatchView( "Halbfinale");
		createMatchView( "Platz 3");
		createMatchView( "Finale");
		
		AdditionalTippsView additionalTippsView = new AdditionalTippsView(user, appWindow);
		additionalTippsView.setImmediate(false);
		additionalTippsView.setWidth("100.0%");
		additionalTippsView.setHeight("100.0%");
		tippTab.addTab(additionalTippsView, "Zusätzliche Tipps");
	}
	
	private void createMatchView(String round)
	{
		String group = "";
		List<UserMatchConnection> data = new ArrayList<UserMatchConnection>();
		data = getMatchesForUserId(round);
		for (int i = 0; i < data.size(); i++) {
			GameMatch gameMatch = TipgameUtils.getGameMatchFromId(data.get(i));
			Session session = DatabaseHelper.getInstance().getHibernateSession();
			session.beginTransaction();
			DatabaseHelper.getInstance().attachPojoToSession(session, gameMatch);
			if(round.equalsIgnoreCase("Vorrunde")) {
				String gameMatchGroup = gameMatch.getPrelimGroup(); 
				if (!group.equalsIgnoreCase(gameMatchGroup))
				{
					Panel preliminaryMatchViewsPanel = new Panel();
					preliminaryMatchViewsPanel.setImmediate(false);
					preliminaryMatchViewsPanel.setWidth("100%");
					preliminaryMatchViewsPanel.setHeight("100%");
					preliminaryMatchViewsPanel.setScrollable(true);

					preliminaryTippLayout = new VerticalLayout();
					preliminaryTippLayout.setImmediate(false);
					preliminaryTippLayout.setWidth("100%");
					preliminaryTippLayout.setHeight("100%");
					preliminaryTippLayout.setMargin(false);
					
					preliminaryMatchViewsPanel.addComponent(preliminaryTippLayout);
					tippTab.addTab(preliminaryMatchViewsPanel, "Gruppe "+gameMatch.getPrelimGroup());
					Label groupLabel = new Label("Gruppe "+gameMatch.getPrelimGroup()+" : ");
					groupLabel.setStyleName(Runo.LABEL_H1);
					preliminaryTippLayout.addComponent(groupLabel);
					group = gameMatch.getPrelimGroup();
				}
			}
			else {
				String gameMatchGroup = gameMatch.getPrelimGroup(); 
				if (!group.equalsIgnoreCase(gameMatchGroup))
				{
					Panel koMatchViewsPanel = new Panel();
					koMatchViewsPanel.setImmediate(false);
					koMatchViewsPanel.setWidth("100%");
					koMatchViewsPanel.setHeight("100%");
					koMatchViewsPanel.setScrollable(true);
					
					koTippLayout = new VerticalLayout();
					koTippLayout.setImmediate(false);
					koTippLayout.setWidth("100%");
					koTippLayout.setHeight("100%");
					koTippLayout.setMargin(false);
					
					koMatchViewsPanel.addComponent(koTippLayout);
					tippTab.addTab(koMatchViewsPanel, round);
					
					Label groupLabel = new Label(round+" : ");
					groupLabel.setStyleName(Runo.LABEL_H1);
					koTippLayout.addComponent(groupLabel);
					group = gameMatch.getPrelimGroup();
				}				
			}
			UserMatchConnection match = data.get(i);
			MatchView matchView = new MatchView(match, user);
			if(round.equalsIgnoreCase("Vorrunde")) {
				preliminaryTippLayout.addComponent(matchView);
			} else {
				koTippLayout.addComponent(matchView);
			}
		}		
	}
	
	private List<UserMatchConnection> getMatchesForUserId(String round)
	{
		List<UserMatchConnection> matches = new ArrayList<UserMatchConnection>();
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
		Session hibernateSession = databaseHelper.getHibernateSession();
		hibernateSession.beginTransaction();
		String order = "";
		if (!round.equalsIgnoreCase("Vorrunde")) {
			order = "order by gameMatchId asc";
		}
		databaseHelper.attachPojoToSession(hibernateSession, user);
		
		Iterator<UserMatchConnection> iter = hibernateSession.createQuery(
			    "from UserMatchConnection where userId = ? and round = ?"+order)
			    .setLong(0, user.getUserID())
			    .setString(1, round)
			    .iterate();
		
		while ( iter.hasNext() ) 
		{
			matches.add(iter.next());
		}
		
		hibernateSession.getTransaction().commit();
		
		return matches;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");

		tippTab = new TabSheet();
		tippTab.setWidth("100%");
		tippTab.setHeight("100%");
		tippTab.setStyleName(Runo.TABSHEET_SMALL);
		mainLayout.addComponent(tippTab);

		return mainLayout;
	}
}
