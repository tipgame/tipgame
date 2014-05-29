package com.tipgame.ui.Home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.vaadin.artur.icepush.ICEPush;

import com.tipgame.data.News;
import com.tipgame.data.Statistic;
import com.tipgame.data.User;
import com.tipgame.database.DatabaseHelper;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.themes.Runo;

public class HomeView extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private GridLayout homeViewGridLayout;
	@AutoGenerated
	private TextArea textArea;
	@AutoGenerated
	private Label labelGreetings;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 3910801321727084159L;
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private Table tableTopTen;
	private User user;
	private DatabaseHelper _DatabaseHelper;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

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
	public HomeView(User user) {
		this.user = user;
		buildMainLayout();
		setCompositionRoot(mainLayout);

		_DatabaseHelper = DatabaseHelper.getInstance();
		setSalutation();
		TipgameUtils.fillTopTenTable(tableTopTen);
		setNewsText();
	}
	
	private void setNewsText()
	{
		Session session = _DatabaseHelper.getHibernateSession();
		session.beginTransaction();
		
		String newsText = "";
		Iterator<News> iter = session.createQuery(
			    "from News order by id desc")
			    .iterate();
		if(iter.hasNext())
		{
			News news = iter.next();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			newsText = sdf.format(news.getTimestamp());
			newsText += " - "+news.getMessage();			
		}
		
		textArea.setValue(newsText);
		
		session.getTransaction().commit();
	}
	
	private void setSalutation()
	{
		Session session = _DatabaseHelper.getHibernateSession();
		session.beginTransaction();
		
		_DatabaseHelper.attachPojoToSession(session, user);
		
		labelGreetings.setValue("Herzlich Willkommen "+user.getChristianname()+" "+user.getName());
		
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
		
		// homeViewGridLayout
		homeViewGridLayout = buildHomeViewGridLayout();
		mainLayout.addComponent(homeViewGridLayout);
		
		return mainLayout;
	}

	@AutoGenerated
	private GridLayout buildHomeViewGridLayout() {
		// common part: create layout
		homeViewGridLayout = new GridLayout();
		homeViewGridLayout.setImmediate(false);
		homeViewGridLayout.setWidth("100.0%");
		homeViewGridLayout.setHeight("100.0%");
		homeViewGridLayout.setMargin(true);
		homeViewGridLayout.setRows(3);
		
		// labelGreetings
		labelGreetings = new Label();
		labelGreetings.setImmediate(false);
		labelGreetings.setWidth("-1px");
		labelGreetings.setHeight("-1px");
		labelGreetings.setValue("Label");
		labelGreetings.setStyleName(Runo.LABEL_H1);
		homeViewGridLayout.addComponent(labelGreetings, 0, 0);
		homeViewGridLayout.setComponentAlignment(labelGreetings, new Alignment(
				48));
		
		// richTextArea_2
		textArea = new TextArea();
		textArea.setImmediate(false);
		textArea.setWidth("100.0%");
		textArea.setHeight("-1px");
		textArea.setEnabled(false);
		homeViewGridLayout.addComponent(textArea, 0, 1);
		
		tableTopTen = new Table();
		tableTopTen.setImmediate(false);
		tableTopTen.setWidth("100.0%");
		tableTopTen.setHeight("250px");
		tableTopTen.addContainerProperty("Rang", String.class,  null);
		tableTopTen.addContainerProperty("Name",  String.class,  null);
		tableTopTen.addContainerProperty("Punkte", String.class, null);
		homeViewGridLayout.addComponent(tableTopTen, 0, 2);
		
		return homeViewGridLayout;
	}

	public Table getTableTopTen() {
		return tableTopTen;
	}

	public void setTableTopTen(Table tableTopTen) {
		this.tableTopTen = tableTopTen;
	}
}
