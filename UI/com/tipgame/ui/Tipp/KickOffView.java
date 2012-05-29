package com.tipgame.ui.Tipp;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class KickOffView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Label LabelKickOff;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 8579726314990507664L;
	
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	public KickOffView(String kickOffDate) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		setKickOffLayout(kickOffDate);
	}

	private void setKickOffLayout(String kickOffDate)
	{
		LabelKickOff.setValue(kickOffDate);
		LabelKickOff.setEnabled(false);
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("160px");
		mainLayout.setHeight("60px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("160px");
		setHeight("60px");
		
		// LabelKickOff
		LabelKickOff = new Label();
		LabelKickOff.setImmediate(false);
		LabelKickOff.setWidth("-1px");
		LabelKickOff.setHeight("-1px");
		LabelKickOff.setValue("01.01.2012 10:00 Uhr");
		mainLayout.addComponent(LabelKickOff);
		mainLayout.setComponentAlignment(LabelKickOff, new Alignment(48));
		
		return mainLayout;
	}

}
