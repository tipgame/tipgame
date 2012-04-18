package com.tipgame.ui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class AwayTeamView extends CustomComponent {

	private static final long serialVersionUID = 7450939758364436744L;
	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private Label LabelAwayTeamName;
	@AutoGenerated
	private Embedded ImageAwayTeam;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	public AwayTeamView(String teamName, String imageSource) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		setAwayTeamView(teamName, imageSource);
	}

	private void setAwayTeamView(String teamName, String imageSource)
	{
		LabelAwayTeamName.setValue(teamName);
		
		/*// ImageAwayTeam
		ImageAwayTeam = new Embedded();
		ImageAwayTeam.setImmediate(false);
		ImageAwayTeam.setWidth("-1px");
		ImageAwayTeam.setHeight("-1px");
		ImageAwayTeam.setSource(new ThemeResource(awayTeam.getImageFlag()));
		ImageAwayTeam.setType(1);
		ImageAwayTeam.setMimeType("image/png");
		mainLayout.addComponent(ImageAwayTeam, 0, 0);
		mainLayout.setComponentAlignment(ImageAwayTeam, new Alignment(48));*/
	}
	
	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("180px");
		mainLayout.setHeight("36px");
		mainLayout.setMargin(false);
		mainLayout.setColumns(2);
		
		// top-level component properties
		setWidth("180px");
		setHeight("36px");
		
		// LabelAwayTeamName
		LabelAwayTeamName = new Label();
		LabelAwayTeamName.setImmediate(false);
		LabelAwayTeamName.setWidth("-1px");
		LabelAwayTeamName.setHeight("-1px");
		LabelAwayTeamName.setValue("Auswärtsteam");
		mainLayout.addComponent(LabelAwayTeamName, 1, 0);
		mainLayout.setComponentAlignment(LabelAwayTeamName, new Alignment(33));
		
		return mainLayout;
	}

}
