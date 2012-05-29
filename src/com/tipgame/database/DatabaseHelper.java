package com.tipgame.database;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.tipgame.data.AllowedUser;
import com.tipgame.data.GameMatch;
import com.tipgame.data.News;
import com.tipgame.data.RankingWinner;
import com.tipgame.data.Statistic;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.data.WorldRankListIFBA;
import com.tipgame.data.WorldRankListOverYears;

public class DatabaseHelper {
	private SessionFactory _SessionFactory;
	private AnnotationConfiguration _Config;
	private static DatabaseHelper instance = new DatabaseHelper();
	
	private DatabaseHelper() {}
	 
    /**
     * Statische Methode, liefert die einzige Instanz dieser
     * Klasse zurück
     */
    public static DatabaseHelper getInstance() {
        return instance;
    }
	
	public Session getHibernateSession()
	{
		CreateSessionFactory();
		return _SessionFactory.getCurrentSession();
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
		config.addAnnotatedClass(RankingWinner.class);
		config.addAnnotatedClass(AllowedUser.class);
		config.addAnnotatedClass(News.class);
		config.addAnnotatedClass(WorldRankListIFBA.class);
		config.addAnnotatedClass(WorldRankListOverYears.class);
		config.configure();
		
		_Config = config;
	}
}
