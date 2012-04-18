package com.tipgame.ui;

import com.tipgame.data.GameResult;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class ResultView extends CustomComponent {

	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private TextField TextFieldResultAwayTeam;
	@AutoGenerated
	private Label LabelResultSeparator;
	@AutoGenerated
	private TextField TextFieldResultHomeTeam;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 5536750775212713714L;
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ResultView(int resultId) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		setGameResult(resultId);
	}
	
	private void setGameResult(int resultId)
	{
		GameResult gameResult = getGameResultTipp(resultId);
		TextFieldResultHomeTeam.setValue(gameResult.getResultHomeTeam());
		TextFieldResultAwayTeam.setValue(gameResult.getResultAwayTeam());
	}
	
	private GameResult getGameResultTipp(int resultTippId)
	{
		GameResult gameResult = new GameResult();
		gameResult.setResultAwayTeam(1);
		gameResult.setResultHomeTeam(2);
		
		return gameResult;
	}

	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("120px");
		mainLayout.setHeight("36px");
		mainLayout.setMargin(false);
		mainLayout.setColumns(3);
		
		// top-level component properties
		setWidth("120px");
		setHeight("36px");
		
		// TextFieldResultHomeTeam
		TextFieldResultHomeTeam = new TextField();
		TextFieldResultHomeTeam.setImmediate(false);
		TextFieldResultHomeTeam.setWidth("30px");
		TextFieldResultHomeTeam.setHeight("-1px");
		TextFieldResultHomeTeam.setSecret(false);
		mainLayout.addComponent(TextFieldResultHomeTeam, 0, 0);
		mainLayout.setComponentAlignment(TextFieldResultHomeTeam,
				new Alignment(48));
		
		// LabelResultSeparator
		LabelResultSeparator = new Label();
		LabelResultSeparator.setImmediate(false);
		LabelResultSeparator.setWidth("-1px");
		LabelResultSeparator.setHeight("-1px");
		LabelResultSeparator.setValue(":");
		mainLayout.addComponent(LabelResultSeparator, 1, 0);
		mainLayout.setComponentAlignment(LabelResultSeparator,
				new Alignment(48));
		
		// TextFieldResultAwayTeam
		TextFieldResultAwayTeam = new TextField();
		TextFieldResultAwayTeam.setImmediate(false);
		TextFieldResultAwayTeam.setWidth("30px");
		TextFieldResultAwayTeam.setHeight("-1px");
		TextFieldResultAwayTeam.setSecret(false);
		mainLayout.addComponent(TextFieldResultAwayTeam, 2, 0);
		mainLayout.setComponentAlignment(TextFieldResultAwayTeam,
				new Alignment(48));
		
		return mainLayout;
	}

}
