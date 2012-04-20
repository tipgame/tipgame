package com.tipgame.ui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class FixtureView extends CustomComponent {

	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private Label LabelVS;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 7537472284099370094L;
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	public FixtureView(String homeTeamName, String homeTeamImage, 
			String awayTeamName, String awayTeamImage) 
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		setHomeTeamView(homeTeamName, homeTeamImage);
		setAwayTeamView(awayTeamName, awayTeamImage);		
	}
	
	private void setHomeTeamView(String teamName, String imageResource)
	{
		HomeTeamView homeTeamView = new HomeTeamView(teamName, imageResource);
		mainLayout.addComponent(homeTeamView, 0, 0);
	}
	
	private void setAwayTeamView(String teamName, String imageResource)
	{
		AwayTeamView homeTeamView = new AwayTeamView(teamName, imageResource);
		mainLayout.addComponent(homeTeamView, 2, 0);
	}
	
	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("420px");
		mainLayout.setHeight("60px");
		mainLayout.setMargin(false);
		mainLayout.setColumns(3);
		
		// top-level component properties
		setWidth("420px");
		setHeight("60px");
		
		// LabelVS
		LabelVS = new Label();
		LabelVS.setImmediate(false);
		LabelVS.setWidth("-1px");
		LabelVS.setHeight("-1px");
		LabelVS.setValue(":");
		mainLayout.addComponent(LabelVS, 1, 0);
		mainLayout.setComponentAlignment(LabelVS, new Alignment(48));
		
		return mainLayout;
	}

}
