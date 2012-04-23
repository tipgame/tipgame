package com.tipgame.ui.Tipp;

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
	public ResultView(String resultHome, String resultAway, Boolean enableFinalResultTextField) {
		buildMainLayout(enableFinalResultTextField);
		setCompositionRoot(mainLayout);

		setGameResult(resultHome, resultAway);
	}
	
	private void setGameResult(String resultHome, String resultAway)
	{
		TextFieldResultHomeTeam.setValue(resultHome);
		TextFieldResultAwayTeam.setValue(resultAway);
	}
	
	public String getTippHomeTeam()
	{
		return (String) TextFieldResultHomeTeam.getValue();
	}
	
	public String getTippAwayTeam()
	{
		return (String) TextFieldResultAwayTeam.getValue();
	}
	
	@AutoGenerated
	private GridLayout buildMainLayout(Boolean enableFinalResultTextField) {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("70px");
		mainLayout.setHeight("60px");
		mainLayout.setMargin(false);
		mainLayout.setColumns(3);
		
		// top-level component properties
		setWidth("70px");
		setHeight("60px");
		
		// TextFieldResultHomeTeam
		TextFieldResultHomeTeam = new TextField();
		TextFieldResultHomeTeam.setImmediate(false);
		TextFieldResultHomeTeam.setWidth("30px");
		TextFieldResultHomeTeam.setHeight("-1px");
		TextFieldResultHomeTeam.setSecret(false);
		TextFieldResultHomeTeam.setEnabled(enableFinalResultTextField);
		
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
		TextFieldResultAwayTeam.setEnabled(enableFinalResultTextField);
		
		mainLayout.addComponent(TextFieldResultAwayTeam, 2, 0);
		mainLayout.setComponentAlignment(TextFieldResultAwayTeam,
				new Alignment(48));
		
		return mainLayout;
	}

}