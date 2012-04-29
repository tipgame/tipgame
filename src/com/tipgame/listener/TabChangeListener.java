package com.tipgame.listener;

import com.vaadin.Application;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Window;

public class TabChangeListener implements TabSheet.SelectedTabChangeListener{

	private static final long serialVersionUID = 549429808817298321L;

	public void selectedTabChange(SelectedTabChangeEvent event) {
		String tmp = event.getTabSheet().getSelectedTab().getCaption();
		if (event.getTabSheet().getSelectedTab().getCaption() == "Logout") {
			event.getTabSheet().getApplication().close();
		}
	}


}
