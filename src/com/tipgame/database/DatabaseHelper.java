package com.tipgame.database;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.tipgame.data.GameMatch;
import com.tipgame.data.Statistic;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;

public class DatabaseHelper {

	private SessionFactory _SessionFactory;
	private AnnotationConfiguration _Config;
	
	
	public Session getHibernateSession()
	{
		CreateSessionFactory();
		return _SessionFactory.openSession();
	}
	
	public void attachPojoToSession(Session session, Object pojo)
	{
		session.lock(pojo, LockMode.NONE);
	}
	
	private void CreateSessionFactory()
	{
		if(_SessionFactory == null)
		{
			if (_Config == null)
			{
				CreateAnnotationConfiguration();
			}
			_SessionFactory = _Config.buildSessionFactory();
		}
	}
	
	private void CreateAnnotationConfiguration()
	{
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(UserMatchConnection.class);
		config.addAnnotatedClass(GameMatch.class);
		config.addAnnotatedClass(Statistic.class);
		config.configure();
		
		_Config = config;
	}
}
