package com.tipgame.ui;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.hibernate.Session;

import com.tipgame.data.Statistic;
import com.tipgame.data.User;
import com.tipgame.database.DatabaseHelper;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.invient.vaadin.charts.InvientCharts;
import com.invient.vaadin.charts.InvientCharts.DecimalPoint;
import com.invient.vaadin.charts.InvientCharts.Series;
import com.invient.vaadin.charts.InvientCharts.SeriesType;
import com.invient.vaadin.charts.InvientCharts.XYSeries;
import com.invient.vaadin.charts.InvientChartsConfig;
import com.invient.vaadin.charts.InvientChartsConfig.CategoryAxis;
import com.invient.vaadin.charts.InvientChartsConfig.Tooltip;
import com.invient.vaadin.charts.InvientChartsConfig.XAxis;

public class OverallStatistic extends CustomComponent {

	private static final long serialVersionUID = 7950889693740485298L;
	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private AbsoluteLayout chartLayout;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public OverallStatistic() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
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
		
		// chartLayout
		chartLayout = new AbsoluteLayout();
		chartLayout.setImmediate(false);
		chartLayout.setWidth("100.0%");
		chartLayout.setHeight("100.0%");
		chartLayout.setMargin(false);
		chartLayout.addComponent(generateChart());
		mainLayout.addComponent(chartLayout, "top:0.0px;left:0.0px;");
		
		return mainLayout;
	}

	private InvientCharts generateChart()
	{
		InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.BAR);

        //chartConfig.getTitle().setText("Column chart with negative values");
        
        chartConfig.setXAxes(setXAxis());

/*        Tooltip tooltip = new Tooltip();
        tooltip.setFormatterJsFunc("function() {"
                + " return '' + this.series.name +': '+ this.y +''; " + "}");
        chartConfig.setTooltip(tooltip);
        chartConfig.getCredit().setEnabled(false);*/

        InvientCharts invChart = new InvientCharts(chartConfig);
        setSeriesData(invChart);
        return invChart;
	}
	
	private void setSeriesData(InvientCharts chart)
	{
		DatabaseHelper databaseHelper = new DatabaseHelper();
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();
		
		Iterator<Statistic> iter = session.createQuery(
			    "from Statistic order by rank desc")
			    .iterate();
		while(iter.hasNext())
		{
			Statistic statistic = iter.next();
			String name = getNameToUserId(statistic.getUserId());
			XYSeries seriesData = new XYSeries(name);
	        seriesData.setSeriesPoints(getPoints(seriesData, statistic.getPoints()));
	        chart.addSeries(seriesData);
		}
		
		session.getTransaction().commit();
	}
	
	private String getNameToUserId(int userId)
	{
		String name = "";
		DatabaseHelper databaseHelper = new DatabaseHelper();
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();
		
		Iterator<User> iter = session.createQuery(
			    "from User where userId = "+String.valueOf(userId))
			    .iterate();
		while(iter.hasNext())
		{
			User user = iter.next();
			name = user.getChristianname()+" "+user.getName();
		}
		
		session.getTransaction().commit();
		
		return name;
	}
	
	private LinkedHashSet<XAxis> setXAxis()
	{
		CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("1", "10", "20", "30", "40", "50", "60", "70", "80",
        		"90", "100", "110", "120", "130", "140", "150"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        
        return xAxesSet;
	}

	private static LinkedHashSet<DecimalPoint> getPoints(Series series,
			double... values) {
		LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
		for (double value : values) {
			points.add(new DecimalPoint(series, value));
		}
		return points;
	}
}
