package com.tipgame.processor;

import com.tipgame.Threads.RegistrationThread;
import com.tipgame.data.User;

public class RegistrationProcessor {	
	
	public void CreateRegistration(User user, String registrationCode) throws Exception
	{				
		RegistrationThread registrationThread = new RegistrationThread(user, registrationCode);
		registrationThread.run();
	}
}
