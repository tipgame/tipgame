package com.tipgame.ui.Statistics;

import java.util.Iterator;
import java.util.LinkedHashSet;
import org.hibernate.Session;

import com.invient.vaadin.charts.InvientCharts;
import com.invient.vaadin.charts.InvientCharts.DecimalPoint;
import com.invient.vaadin.charts.InvientCharts.SeriesType;
import com.invient.vaadin.charts.InvientCharts.XYSeries;
import com.invient.vaadin.charts.InvientChartsConfig;
import com.invient.vaadin.charts.InvientChartsConfig.PieConfig;
import com.invient.vaadin.charts.InvientChartsConfig.PieDataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.PointConfig;
import com.tipgame.data.RankingWinner;
import com.tipgame.database.DatabaseHelper;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;

public class EMWinnerStatistic extends CustomComponent {

	private static final long serialVersionUID = -6172614589039461744L;
	@AutoGenerated
	private GridLayout mainLayout;


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
	public EMWinnerStatistic() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		generateChart();
	}

	private void generateChart()
	{
		InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.PIE);

        chartConfig.getTitle().setText(
                "�bersicht �ber alle Europameister");

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '<b>'+ this.point.name +'</b>: '+ this.y"
                                + "}");

        PieConfig pie = new PieConfig();
        pie.setAllowPointSelect(true);
        pie.setCursor("pointer");
        pie.setDataLabel(new PieDataLabel(false));
        pie.setShowInLegend(true);
        chartConfig.addSeriesConfig(pie);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries series = new XYSeries("Europameister");
        

        series.setSeriesPoints(createPoints(series));
        chart.addSeries(series);

       mainLayout.addComponent(chart);
	}
	
	private LinkedHashSet<DecimalPoint> createPoints(XYSeries series)
	{
		LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
		Session session = databaseHelper.getHibernateSession();
		session.beginTransaction();
		
		Iterator<RankingWinner> iter = session.createQuery(
			    "from RankingWinner where tournament = 'EM' order by wins desc")
			    .iterate();
		Integer index = 0;
		while(iter.hasNext())
		{
			RankingWinner winner = iter.next();
			String xCord = winner.getCountry()+" ("+winner.getYear()+" )";
			if(index == 0) {
				PointConfig config = new PointConfig(true);
				points.add(new DecimalPoint(series, xCord, winner.getWins(), config));
			}
			else
				points.add(new DecimalPoint(series, xCord, winner.getWins()));
			index++;
		}
		
		session.getTransaction().commit();
		return points;
	}
	

	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		return mainLayout;
	}

}
