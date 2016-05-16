package com.tipgame.Administration;

import org.hibernate.Session;

import com.tipgame.data.GameMatch;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.ui.Tipp.FixtureView;
import com.tipgame.ui.Tipp.ResultView;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;

public class FinalResultAddView extends CustomComponent {

	@AutoGenerated
	private GridLayout mainLayout;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 2278346786920999261L;

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private DatabaseHelper databaseHelper;
	private GameMatch match;
	private ResultView resultView;
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public FinalResultAddView(GameMatch match) {
		this.match = match;
		this.databaseHelper = DatabaseHelper.getInstance();
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		setAddFinalResultView();
	}

	private void setAddFinalResultView()
	{
				
		FixtureView fixture = new FixtureView(match.getHomeTeamName(), match.getHomeTeamImage(),
				match.getAwayTeamName(), match.getAwayTeamImage());
		fixture.setGameMatch(match);
		mainLayout.addComponent(fixture, 0, 0);
		
		resultView = new ResultView(match.getResultFinalHomeTeam(), match.getResultFinalAwayTeam(), true, "", false);
		mainLayout.addComponent(resultView, 1, 0);
		
		Button button = new Button("", this, "onSubmitFinalResultButtonClick");
		button.setCaption("Resultat eintragen");
		mainLayout.addComponent(button, 2, 0);
		mainLayout.setComponentAlignment(button, new Alignment(48));
	}
	
	
	public void onSubmitFinalResultButtonClick(Button.ClickEvent event)
	{
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();
		databaseHelper.attachPojoToSession(session, match);
		String group = match.getGroup();
		String round = match.getRound();
		
		match.setResultFinalHomeTeam(resultView.getResultHomeTeam());
		match.setResultFinalAwayTeam(resultView.getResultAwayTeam());
		
		session.saveOrUpdate(match);
		
		session.getTransaction().commit();
		
	}
	
	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("60px");
		mainLayout.setMargin(false);
		mainLayout.setColumns(4);
		// top-level component properties
		setWidth("100.0%");
		setHeight("60px");
		
		return mainLayout;
	}

}
