package com.tipgame.ui.Registration;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class RegistrationWindow extends Window
{
	private static final long serialVersionUID = 5642854746610306336L;
	
	public RegistrationWindow(String caption, ComponentContainer view) {
        setModal(true);
        setWidth("350px");
        setHeight("500px");
        center();
        
        setCaption(caption);
        
        setContent(view);
    }
}
