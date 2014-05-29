package com.tipgame.listener;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;

public class TabChangeListener implements TabSheet.SelectedTabChangeListener{

	private static final long serialVersionUID = 549429808817298321L;

	public void selectedTabChange(SelectedTabChangeEvent event) {
		if (event.getTabSheet().getSelectedTab().getCaption() == "Logout") {
			event.getTabSheet().getApplication().close();
		}
	}


}
