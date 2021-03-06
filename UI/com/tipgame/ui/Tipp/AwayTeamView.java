package com.tipgame.ui.Tipp;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AwayTeamView extends CustomComponent {

	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private Label LabelAwayTeamName;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 7450939758364436744L;
	

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
		
		Embedded AwayTeamImage = new Embedded();
		AwayTeamImage.setImmediate(false);
		AwayTeamImage.setWidth("30px");
		AwayTeamImage.setHeight("30px");
		AwayTeamImage.setSource(new ThemeResource(imageSource));
		AwayTeamImage.setType(1);
		AwayTeamImage.setMimeType("image/png");
		mainLayout.addComponent(AwayTeamImage, 0, 0);
		mainLayout.setComponentAlignment(AwayTeamImage, new Alignment(48));
	}
	
	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("300px");
		mainLayout.setHeight("60px");
		mainLayout.setMargin(false);
		mainLayout.setColumns(2);
		
		// top-level component properties
		setWidth("300px");
		setHeight("60px");
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		mainLayout.addComponent(verticalLayout_1, 1, 0);
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);
		
		// LabelAwayTeamName
		LabelAwayTeamName = new Label();
		LabelAwayTeamName.setImmediate(false);
		LabelAwayTeamName.setWidth("-1px");
		LabelAwayTeamName.setHeight("-1px");
		LabelAwayTeamName.setValue("Auswärtsteam");
		verticalLayout_1.addComponent(LabelAwayTeamName);
		verticalLayout_1.setComponentAlignment(LabelAwayTeamName,
				new Alignment(33));
		
		return verticalLayout_1;
	}

}
