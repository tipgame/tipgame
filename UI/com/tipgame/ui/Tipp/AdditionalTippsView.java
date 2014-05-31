package com.tipgame.ui.Tipp;

import java.util.Calendar;

import org.hibernate.Session;

import com.tipgame.data.User;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class AdditionalTippsView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private ComboBox CboxTippWinner;

	@AutoGenerated
	private Label LabelWinner;

	@AutoGenerated
	private GridLayout gridLayout_setTippGermany;

	@AutoGenerated
	private Button BtnTippGermany;

	@AutoGenerated
	private ComboBox CboxTippGermany;

	@AutoGenerated
	private Label LabelTippGermany;

	@AutoGenerated
	private GridLayout gridLayout_setTippWinner;

	@AutoGenerated
	private Button BtnWinner;


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private DatabaseHelper databaseHelper;
	private User user;
	private Window appWindow;


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 2111601233339166221L;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 * @param appWindow 
	 */	
	public AdditionalTippsView(User user, Window appWindow) {
		this.user = user;
		this.appWindow = appWindow;
		
		databaseHelper = DatabaseHelper.getInstance();
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		setCboxTippGermanyValues();
		setCboxTippWinnerValues();
		setUserChoices();
		enableOrDisableAdditionalTipps();
		CheckIfUserAlreadyMadeHisDecision(user);
	}
	
	private void CheckIfUserAlreadyMadeHisDecision(User user)
	{
		if (!user.getGermanytipp().isEmpty()) {			
			((Button)gridLayout_setTippGermany.getComponent(0, 0)).setCaption("Tipp entfernen");
		}
		else
		{
			((Button)gridLayout_setTippGermany.getComponent(0, 0)).setCaption("Tipp abgeben");
		}
		
		if(!user.getWinnertipp().isEmpty()) {
			((Button)gridLayout_setTippWinner.getComponent(0, 0)).setCaption("Tipp entfernen");
		}
		else 
		{
			((Button)gridLayout_setTippWinner.getComponent(0, 0)).setCaption("Tipp abgeben");
		}
	}
	
	public void onButtonClick (Button.ClickEvent event) {
        if (event.getButton() == BtnTippGermany)
        {
        	if(event.getButton().getCaption() == "Tipp abgeben") {
        		String value = (String) CboxTippGermany.getValue();
        		if (value == null || value.isEmpty())
        			appWindow.showNotification("Bitte gib einen Tipp ab, wie weit Deutschland kommen wird.");
        		else {
        			setAdditionalTipp(false);
        			CheckIfUserAlreadyMadeHisDecision(user);
        		}
        	}
        		
        	else
        	{
        		removeAdditionalTipp(false);
        		CheckIfUserAlreadyMadeHisDecision(user);
        		CboxTippGermany.select(CboxTippGermany.getNullSelectionItemId());
        	}
        }           
        else if (event.getButton() == BtnWinner)
        {
        	if(event.getButton().getCaption() == "Tipp abgeben") { 
        		String value = (String) CboxTippWinner.getValue();
        		if (value == null || value.isEmpty())
        			appWindow.showNotification("Bitte gib einen Tipp ab wer Weltmeister wird.");
        		else {
        			setAdditionalTipp(true);
        			CheckIfUserAlreadyMadeHisDecision(user);
        		}
        	}
        	else
        	{
        		removeAdditionalTipp(true);
        		CheckIfUserAlreadyMadeHisDecision(user); 
        		CboxTippWinner.select(CboxTippWinner.getNullSelectionItemId());
        	}
        }
    }
	
	private void enableOrDisableAdditionalTipps()
	{
		Boolean setEnabled = TipgameUtils.compareDates("12.06.2014 21:55");
		
		CboxTippGermany.setEnabled(!setEnabled);
		CboxTippWinner.setEnabled(!setEnabled);
		BtnTippGermany.setEnabled(!setEnabled);
		BtnWinner.setEnabled(!setEnabled);
	}
	
	private void setAdditionalTipp(Boolean isWinnerTipp)
	{
		Session session = databaseHelper.getHibernateSession();
		try {
			session.beginTransaction();
			if(isWinnerTipp)
			{
				if (user.getWinnertipp().isEmpty())
				{
					user.setWinnertipp((String) CboxTippWinner.getValue());
				}
			}
			else
			{
				if(user.getGermanytipp().isEmpty())
				{
					user.setGermanytipp((String) CboxTippGermany.getValue());
				}
			}
			session.saveOrUpdate(user);			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
			
	}
	
	private void removeAdditionalTipp(Boolean isWinnerTipp) 
	{
		Session session = databaseHelper.getHibernateSession();
		try {
			session.beginTransaction();
			if(isWinnerTipp)
			{
				user.setWinnertipp("");
			}
			else
			{
				user.setGermanytipp("");
			}
			session.saveOrUpdate(user);			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	private void setCboxTippGermanyValues()
	{
		CboxTippGermany.addItem("Vorrunde");
		CboxTippGermany.addItem("Achtelfinale");
		CboxTippGermany.addItem("Viertelfinale");
		CboxTippGermany.addItem("Halbfinale");
		CboxTippGermany.addItem("Finale");
		CboxTippGermany.addItem("Weltmeister");
	}
	
	private void setCboxTippWinnerValues()
	{
		CboxTippWinner.addItem("Algerien");
		CboxTippWinner.addItem("Argentinien");
		CboxTippWinner.addItem("Australien");
		CboxTippWinner.addItem("Belgien");
		CboxTippWinner.addItem("Bosnien-Herzegowina");
		CboxTippWinner.addItem("Brasilien");
		CboxTippWinner.addItem("Chile");
		CboxTippWinner.addItem("Costa Rica");
		CboxTippWinner.addItem("Deutschland");
		CboxTippWinner.addItem("Ecuador");
		CboxTippWinner.addItem("Elfenbeink�ste");
		CboxTippWinner.addItem("England");
		CboxTippWinner.addItem("Frankreich");
		CboxTippWinner.addItem("Ghana");
		CboxTippWinner.addItem("Griechenland");
		CboxTippWinner.addItem("Honduras");
		CboxTippWinner.addItem("Iran");
		CboxTippWinner.addItem("Italien");
		CboxTippWinner.addItem("Japan");
		CboxTippWinner.addItem("Kamerun");
		CboxTippWinner.addItem("Kolumbien");
		CboxTippWinner.addItem("Kroatien");
		CboxTippWinner.addItem("Mexico");
		CboxTippWinner.addItem("Niederlande");
		CboxTippWinner.addItem("Nigeria");
		CboxTippWinner.addItem("Portugal");
		CboxTippWinner.addItem("Russland");
		CboxTippWinner.addItem("Schweiz");
		CboxTippWinner.addItem("Spanien");
		CboxTippWinner.addItem("S�dkorea");
		CboxTippWinner.addItem("Uruguay");
		CboxTippWinner.addItem("USA");
		
	}
	
	private void setUserChoices()
	{
		CboxTippGermany.select(user.getGermanytipp());
		CboxTippWinner.select(user.getWinnertipp());
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		mainLayout.addComponent(gridLayout_1);
		
		return mainLayout;
	}


	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("100.0%");
		gridLayout_1.setHeight("50.0%");
		gridLayout_1.setMargin(true);
		gridLayout_1.setColumns(3);
		gridLayout_1.setRows(4);
		
		// gridLayout_3
		gridLayout_setTippWinner = buildGridLayout_3();
		gridLayout_1.addComponent(gridLayout_setTippWinner, 2, 3);
		
		// LabelTippGermany
		LabelTippGermany = new Label();
		LabelTippGermany.setImmediate(false);
		LabelTippGermany.setWidth("-1px");
		LabelTippGermany.setHeight("-1px");
		LabelTippGermany.setValue("Wie weit kommt Deutschland");
		gridLayout_1.addComponent(LabelTippGermany, 0, 2);
		gridLayout_1.setComponentAlignment(LabelTippGermany, new Alignment(20));
		
		// CboxTippGermany
		CboxTippGermany = new ComboBox();
		CboxTippGermany.setImmediate(false);
		CboxTippGermany.setWidth("-1px");
		CboxTippGermany.setHeight("-1px");
		gridLayout_1.addComponent(CboxTippGermany, 1, 2);
		
		// gridLayout_2
		gridLayout_setTippGermany = buildGridLayout_2();
		gridLayout_1.addComponent(gridLayout_setTippGermany, 2, 2);
		
		// LabelWinner
		LabelWinner = new Label();
		LabelWinner.setImmediate(false);
		LabelWinner.setWidth("-1px");
		LabelWinner.setHeight("-1px");
		LabelWinner.setValue("Weltmeister");
		gridLayout_1.addComponent(LabelWinner, 0, 3);
		gridLayout_1.setComponentAlignment(LabelWinner, new Alignment(20));
		
		// CboxTippWinner
		CboxTippWinner = new ComboBox();
		CboxTippWinner.setImmediate(false);
		CboxTippWinner.setWidth("-1px");
		CboxTippWinner.setHeight("-1px");
		gridLayout_1.addComponent(CboxTippWinner, 1, 3);
		
		return gridLayout_1;
	}


	@AutoGenerated
	private GridLayout buildGridLayout_3() {
		// common part: create layout
		gridLayout_setTippWinner = new GridLayout();
		gridLayout_setTippWinner.setImmediate(false);
		gridLayout_setTippWinner.setWidth("-1px");
		gridLayout_setTippWinner.setHeight("-1px");
		gridLayout_setTippWinner.setMargin(false);
		gridLayout_setTippWinner.setColumns(2);
		
		// BtnWinner
		BtnWinner = new Button("",this,"onButtonClick");
		BtnWinner.setCaption("Tipp abgeben");
		BtnWinner.setImmediate(true);
		BtnWinner.setWidth("-1px");
		BtnWinner.setHeight("-1px");
		BtnWinner.setStyleName(Reindeer.BUTTON_LINK);
		gridLayout_setTippWinner.addComponent(BtnWinner, 0, 0);
		
		return gridLayout_setTippWinner;
	}


	@AutoGenerated
	private GridLayout buildGridLayout_2() {
		// common part: create layout
		gridLayout_setTippGermany = new GridLayout();
		gridLayout_setTippGermany.setImmediate(false);
		gridLayout_setTippGermany.setWidth("-1px");
		gridLayout_setTippGermany.setHeight("-1px");
		gridLayout_setTippGermany.setMargin(false);
		gridLayout_setTippGermany.setColumns(2);
		
		
		// BtnTippGermany
		Boolean setButtonEnabled = TipgameUtils.compareDates("12.06.2014 21:55");
		BtnTippGermany = new Button("",this,"onButtonClick");
		BtnTippGermany.setCaption("Tipp abgeben");
		BtnTippGermany.setImmediate(true);
		BtnTippGermany.setWidth("-1px");
		BtnTippGermany.setHeight("-1px");
		BtnTippGermany.setStyleName(Reindeer.BUTTON_LINK);
		BtnTippGermany.setEnabled(!setButtonEnabled);
		gridLayout_setTippGermany.addComponent(BtnTippGermany, 0, 0);
		
		return gridLayout_setTippGermany;
	}
}
