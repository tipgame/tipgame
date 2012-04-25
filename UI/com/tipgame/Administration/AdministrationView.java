package com.tipgame.Administration;

import java.util.Iterator;

import org.hibernate.Session;

import com.tipgame.data.GameMatch;
import com.tipgame.database.DatabaseHelper;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class AdministrationView extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private TabSheet adminTabSheet;
	@AutoGenerated
	private Panel mainPanel;
	@AutoGenerated
	private VerticalLayout verticalLayoutOnPanel;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

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
	public AdministrationView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		setMatchesOnView();
	}

	private void setMatchesOnView()
	{	
		DatabaseHelper databaseHelper = new DatabaseHelper();
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();
		
		Iterator<GameMatch> iter = session.createQuery(
			    "from GameMatch ").iterate();
		while (iter.hasNext())
		{
			GameMatch match = iter.next();
			FinalResultAddView finalResultAddView = new FinalResultAddView(match);
			verticalLayoutOnPanel.addComponent(finalResultAddView);
		}
		
		session.getTransaction().commit();
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
		
		// tabSheet_1
		adminTabSheet = new TabSheet();
		adminTabSheet.setImmediate(false);
		adminTabSheet.setWidth("100.0%");
		adminTabSheet.setHeight("100.0%");
		mainLayout.addComponent(adminTabSheet, "top:0.0px;left:0.0px;");
		
		// mainPanel
		mainPanel = buildMainPanel();
		adminTabSheet.addTab(mainPanel, "Spielergebnisse");
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildMainPanel() {
		// common part: create layout
		mainPanel = new Panel();
		mainPanel.setImmediate(false);
		mainPanel.setWidth("100.0%");
		mainPanel.setHeight("100.0%");
		
		// verticalLayoutOnPanel
		verticalLayoutOnPanel = new VerticalLayout();
		verticalLayoutOnPanel.setImmediate(false);
		verticalLayoutOnPanel.setWidth("100.0%");
		verticalLayoutOnPanel.setHeight("100.0%");
		verticalLayoutOnPanel.setMargin(false);
		mainPanel.setContent(verticalLayoutOnPanel);
		
		return mainPanel;
	}

}
