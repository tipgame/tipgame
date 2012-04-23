package com.tipgame.ui.Statistics;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.Runo;

public class StatisticView extends CustomComponent {

	private static final long serialVersionUID = 6596452272451577509L;
	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private TabSheet statisticTabSheet;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public StatisticView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		OverallStatistic overallStatistic = new OverallStatistic();
		statisticTabSheet.addTab(overallStatistic, "Platzierung");
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// statisticTabSheet
		statisticTabSheet = new TabSheet();
		statisticTabSheet.setImmediate(false);
		statisticTabSheet.setWidth("100.0%");
		statisticTabSheet.setHeight("100.0%");
		statisticTabSheet.setStyleName(Runo.TABSHEET_SMALL);
		mainLayout.addComponent(statisticTabSheet, "top:0.0px;left:0.0px;");
		
		return mainLayout;
	}
}