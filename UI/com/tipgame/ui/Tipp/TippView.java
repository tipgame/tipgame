package com.tipgame.ui.Tipp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.vaadin.artur.icepush.ICEPush;

import sun.swing.UIAction;

import com.tipgame.data.GameMatch;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Runo;
import com.vaadin.ui.ProgressIndicator;

public class TippView extends CustomComponent {

	private static final long serialVersionUID = -3251458515243143780L;
	
	@AutoGenerated
	private VerticalLayout mainLayout;
	private VerticalLayout preliminaryTippLayout;
	private VerticalLayout koTippLayout;
	private User user;
	private Window appWindow;

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
		this.appWindow = appWindow;
		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		createMatchView(preliminaryTippLayout, "Vorrunde");
		createMatchView(koTippLayout, "Achtelfinale");
		createMatchView(koTippLayout, "Viertelfinale");
		createMatchView(koTippLayout, "Halbfinale");
		createMatchView(koTippLayout, "Finale");
	}
	
	private void createMatchView(VerticalLayout tippLayout, String round)
	{
		String group = "";
		List<UserMatchConnection> data = new ArrayList<UserMatchConnection>();
		data = getMatchesForUserId(round);
		int viewOnLayoutIndex = 0;
		for (int i = 0; i < data.size(); i++) {
			GameMatch gameMatch = TipgameUtils.getGameMatchFromId(data.get(i));
			Session session = DatabaseHelper.getInstance().getHibernateSession();
			session.beginTransaction();
			DatabaseHelper.getInstance().attachPojoToSession(session, gameMatch);
			if(round.equalsIgnoreCase("Vorrunde")) {
				String gameMatchGroup = gameMatch.getGroup(); 
				if (!group.equalsIgnoreCase(gameMatchGroup))
				{
					Label groupLabel = new Label("Gruppe "+gameMatch.getGroup()+" : ");
					groupLabel.setStyleName(Runo.LABEL_H1);
					tippLayout.addComponent(groupLabel);
					group = gameMatch.getGroup();
					viewOnLayoutIndex++;
				}
			}
			else {
				String gameMatchGroup = gameMatch.getGroup(); 
				if (!group.equalsIgnoreCase(gameMatchGroup))
				{
					Label groupLabel = new Label(round+" : ");
					groupLabel.setStyleName(Runo.LABEL_H1);
					tippLayout.addComponent(groupLabel);
					group = gameMatch.getGroup();
					viewOnLayoutIndex++;
				}				
			}
			UserMatchConnection match = data.get(i);
			MatchView matchView = new MatchView(match, user);
			if(round.equalsIgnoreCase("Vorrunde")) {
				tippLayout.addComponent(matchView, viewOnLayoutIndex);
			} else {
				tippLayout.addComponent(matchView);
			}
			
			viewOnLayoutIndex++;
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

		TabSheet tippTab = new TabSheet();
		tippTab.setWidth("100%");
		tippTab.setHeight("100%");
		tippTab.setStyleName(Runo.TABSHEET_SMALL);
		mainLayout.addComponent(tippTab);
		
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
		tippTab.addTab(preliminaryMatchViewsPanel, "Tipps Vorrunde");
		
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
		tippTab.addTab(koMatchViewsPanel, "Tipps KO-Runde");
		
		AdditionalTippsView additionalTippsView = new AdditionalTippsView(user, appWindow);
		additionalTippsView.setImmediate(false);
		additionalTippsView.setWidth("100.0%");
		additionalTippsView.setHeight("100.0%");
		tippTab.addTab(additionalTippsView, "Zusätzliche Tipps");
		
		return mainLayout;
	}
}
