package com.tipgame.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.tipgame.data.GameMatch;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;

public class DatabaseHelper {

	private SessionFactory _SessionFactory;
	private AnnotationConfiguration _Config;
	
	
	public Session GetHibernateSession()
	{
		CreateSessionFactory();
		return _SessionFactory.getCurrentSession();
	}
	
	private void CreateSessionFactory()
	{
		CreateAnnotationConfiguration();
		_SessionFactory = _Config.buildSessionFactory();
	}
	
	private void CreateAnnotationConfiguration()
	{
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(UserMatchConnection.class);
		config.addAnnotatedClass(GameMatch.class);
		config.configure();
		
		_Config = config;
	}
}
