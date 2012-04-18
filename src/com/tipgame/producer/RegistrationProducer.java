package com.tipgame.producer;

import org.hibernate.Session;

import com.tipgame.data.User;
import com.tipgame.database.DatabaseHelper;

public class RegistrationProducer {	
	public Boolean CreateRegistration(User user)
	{
		DatabaseHelper databaseHelper = new DatabaseHelper();
		Session hibernateSession = databaseHelper.GetHibernateSession();
		
		hibernateSession.beginTransaction();
		
		hibernateSession.save(user);
		
		hibernateSession.getTransaction().commit();
		
		return true;
	}
}
