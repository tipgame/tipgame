package com.tipgame.processor;

import com.tipgame.Threads.RegistrationThread;
import com.tipgame.data.User;
import com.vaadin.ui.ProgressIndicator;

public class RegistrationProcessor {	
	
	private ProgressIndicator progressIndicator;
	
	public RegistrationProcessor(ProgressIndicator progressIndicator)
	{
		this.progressIndicator = progressIndicator;
	}
	
	public void CreateRegistration(User user) throws Exception
	{				
		RegistrationThread registrationThread = new RegistrationThread(progressIndicator, user);
		registrationThread.run();
	}
}
