package com.tipgame.ui.Tipp;

import com.github.wolfie.refresher.Refresher;
import com.tipgame.Threads.DateCheckThread;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class ResultView extends CustomComponent {

	private static final long serialVersionUID = 5536750775212713714L;
	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private TextField TextFieldResultAwayTeam;
	@AutoGenerated
	private Label LabelResultSeparator;
	@AutoGenerated
	private TextField TextFieldResultHomeTeam;
	
	private Button setTippButton;
	private Button removeTippButton;

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
	public ResultView(String resultHome, String resultAway, Boolean enableFinalResultTextField, 
			String kickOffTimestamp, Boolean useRefresher) {
		
		buildMainLayout(enableFinalResultTextField);
		setCompositionRoot(mainLayout);
		if (useRefresher)
		{
			if (!(TipgameUtils.isTimeToDisableTippFields(kickOffTimestamp)))
			{
				Refresher refresher = new Refresher();
				refresher.setRefreshInterval(120000);
				mainLayout.addComponent(refresher, 3, 0);
			}
			else
			{
				if (setTippButton != null)
					setTippButton.setEnabled(false);
				if(setTippButton != null)
					setTippButton.setEnabled(false);
				if(TextFieldResultHomeTeam != null)
					TextFieldResultHomeTeam.setEnabled(false);
				if(TextFieldResultAwayTeam != null)
					TextFieldResultAwayTeam.setEnabled(false);
			}
		}
		setGameResult(resultHome, resultAway);
	}
	
	private void setGameResult(String resultHome, String resultAway)
	{
		TextFieldResultHomeTeam.setValue(resultHome);
		TextFieldResultAwayTeam.setValue(resultAway);
	}
	
	public String getResultHomeTeam()
	{
		return (String) TextFieldResultHomeTeam.getValue();
	}
	
	public String getResultAwayTeam()
	{
		return (String) TextFieldResultAwayTeam.getValue();
	}
	
	public void setResultHomeTeam(String result)
	{
		TextFieldResultHomeTeam.setValue(result);
	}
	
	public void setResultAwayTeam(String result)
	{
		TextFieldResultAwayTeam.setValue(result);
	}
	
	@AutoGenerated
	private GridLayout buildMainLayout(Boolean enableFinalResultTextField) {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("70px");
		mainLayout.setHeight("60px");
		mainLayout.setMargin(false);
		mainLayout.setColumns(4);
		
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

	public Button getSetTippButton() {
		return setTippButton;
	}

	public void setSetTippButton(Button setTippButton) {
		this.setTippButton = setTippButton;
	}

	public Button getRemoveTippButton() {
		return removeTippButton;
	}

	public void setRemoveTippButton(Button removeTippButton) {
		this.removeTippButton = removeTippButton;
	}

}
