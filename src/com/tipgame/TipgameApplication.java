package com.tipgame;

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
