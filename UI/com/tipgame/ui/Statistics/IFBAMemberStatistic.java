package com.tipgame.ui.Statistics;

import java.util.Arrays;
import java.util.LinkedHashSet;

import com.invient.vaadin.charts.InvientCharts;
import com.invient.vaadin.charts.InvientCharts.DecimalPoint;
import com.invient.vaadin.charts.InvientCharts.Series;
import com.invient.vaadin.charts.InvientCharts.SeriesType;
import com.invient.vaadin.charts.InvientCharts.XYSeries;
import com.invient.vaadin.charts.InvientChartsConfig;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.AxisTitle;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotLine;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotLine.NumberValue;
import com.invient.vaadin.charts.InvientChartsConfig.CategoryAxis;
import com.invient.vaadin.charts.InvientChartsConfig.GeneralChartConfig.Margin;
import com.invient.vaadin.charts.InvientChartsConfig.HorzAlign;
import com.invient.vaadin.charts.InvientChartsConfig.Legend;
import com.invient.vaadin.charts.InvientChartsConfig.Legend.Layout;
import com.invient.vaadin.charts.InvientChartsConfig.LineConfig;
import com.invient.vaadin.charts.InvientChartsConfig.NumberYAxis;
import com.invient.vaadin.charts.InvientChartsConfig.Position;
import com.invient.vaadin.charts.InvientChartsConfig.VertAlign;
import com.invient.vaadin.charts.InvientChartsConfig.XAxis;
import com.invient.vaadin.charts.InvientChartsConfig.YAxis;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;

public class IFBAMemberStatistic extends CustomComponent {

	private static final long serialVersionUID = 6478286542530551745L;
	@AutoGenerated
	private GridLayout mainLayout;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public IFBAMemberStatistic() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		generateChart();
	}
	
	private void generateChart() {
		InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.LINE);
        chartConfig.getGeneralChartConfig().setMargin(new Margin());
        chartConfig.getGeneralChartConfig().getMargin().setRight(130);
        chartConfig.getGeneralChartConfig().getMargin().setBottom(25);

        chartConfig.getTitle().setX(-20);
        chartConfig.getTitle().setText("Mitgliederentwicklung IFBA");
        chartConfig.getTitle().setX(-20);

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setCategories(Arrays.asList("2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(categoryAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis numberYAxis = new NumberYAxis();
        numberYAxis.setTitle(new AxisTitle("Anzahl der Mitglieder"));
        NumberPlotLine plotLine = new NumberPlotLine("TempAt0");
        plotLine.setValue(new NumberValue(0.0));
        plotLine.setWidth(1);
        plotLine.setZIndex(1);
        //plotLine.setColor(new RGB(128, 128, 128));
        numberYAxis.addPlotLine(plotLine);
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(numberYAxis);
        chartConfig.setYAxes(yAxesSet);

        Legend legend = new Legend();
        legend.setLayout(Layout.VERTICAL);
        Position legendPos = new Position();
        legendPos.setAlign(HorzAlign.RIGHT);
        legendPos.setVertAlign(VertAlign.TOP);
        legendPos.setX(-10);
        legendPos.setY(100);
        legend.setPosition(legendPos);
        legend.setBorderWidth(0);
        chartConfig.setLegend(legend);

        // Series data label formatter
        LineConfig lineCfg = new LineConfig();
        chartConfig.addSeriesConfig(lineCfg);


        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries seriesData = new XYSeries("IFBA Mitglieder");
        seriesData.setSeriesPoints(getPoints(seriesData, 1.0, 20.0, 30.0, 40.0,
                50.0, 60.0, 70.0, 80.0, 90.0, 100.0));
        chart.addSeries(seriesData);

        mainLayout.addComponent(chart);
	}
	
	
	private static LinkedHashSet<DecimalPoint> getPoints(Series series,
			double... values) {
		LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
		for (double value : values) {
			points.add(new DecimalPoint(series, value));
		}
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
