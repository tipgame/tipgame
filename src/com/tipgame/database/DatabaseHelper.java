package com.tipgame.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.tipgame.data.GameResult;
import com.tipgame.data.KickOff;
import com.tipgame.data.Match;
import com.tipgame.data.Team;
import com.tipgame.data.User;

public class DatabaseHelper {

	private SessionFactory _SessionFactory;
	private AnnotationConfiguration _Config;
	
	
	public Session GetHibernateSession()
	{
		if (_SessionFactory == null)
		{
			CreateSessionFactory();
		}
		return _SessionFactory.getCurrentSession();
	}
	
	private SessionFactory CreateSessionFactory()
	{
		if (_SessionFactory == null){
			
			if (_Config == null)
			{
				CreateAnnotationConfiguration();
			}
			_SessionFactory = _Config.buildSessionFactory();
		}
		return _SessionFactory;
	}
	
	private void CreateAnnotationConfiguration()
	{
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(KickOff.class);
		config.addAnnotatedClass(Match.class);
		config.addAnnotatedClass(Team.class);
		config.addAnnotatedClass(GameResult.class);
		config.configure();
		
		_Config = config;
	}
}
