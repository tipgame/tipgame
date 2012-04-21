package com.tipgame;

import java.util.Arrays;
import java.util.LinkedHashSet;

import com.invient.vaadin.charts.InvientCharts;
import com.invient.vaadin.charts.InvientChartsConfig;
import com.invient.vaadin.charts.Color.RGB;
import com.invient.vaadin.charts.InvientCharts.DecimalPoint;
import com.invient.vaadin.charts.InvientCharts.Series;
import com.invient.vaadin.charts.InvientCharts.SeriesType;
import com.invient.vaadin.charts.InvientCharts.XYSeries;
import com.invient.vaadin.charts.InvientChartsConfig.AreaConfig;
import com.invient.vaadin.charts.InvientChartsConfig.CategoryAxis;
import com.invient.vaadin.charts.InvientChartsConfig.HorzAlign;
import com.invient.vaadin.charts.InvientChartsConfig.Legend;
import com.invient.vaadin.charts.InvientChartsConfig.NumberYAxis;
import com.invient.vaadin.charts.InvientChartsConfig.Position;
import com.invient.vaadin.charts.InvientChartsConfig.VertAlign;
import com.invient.vaadin.charts.InvientChartsConfig.XAxis;
import com.invient.vaadin.charts.InvientChartsConfig.YAxis;
import com.invient.vaadin.charts.InvientChartsConfig.YAxisDataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.AxisTitle;
import com.invient.vaadin.charts.InvientChartsConfig.Legend.Layout;
import com.tipgame.ui.MainView;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.Application;
import com.vaadin.ui.*;

public class TipgameApplication extends Application {
	private static final long serialVersionUID = -5161118814136636688L;

	@Override
	public void init() {
		BuildMainLayout();
	}
	
	private void BuildMainLayout()
	{	
		Window mainWindow = new Window("Tipgame v1.0");
		mainWindow.setTheme("tipgametheme");
		setMainWindow(mainWindow);
		MainView mainview = new MainView();		 
		mainWindow.setContent(mainview);
	}
}
