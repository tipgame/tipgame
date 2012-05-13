package com.tipgame.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import com.tipgame.data.GameMatch;
import com.tipgame.data.Statistic;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;
import com.tipgame.database.DatabaseHelper;
import com.vaadin.ui.Table;

public class TipgameUtils {
	
	public static byte[] computeHash(String x)   
			  throws Exception  
			  {
			     java.security.MessageDigest d =null;
			     d = java.security.MessageDigest.getInstance("SHA-1");
			     d.reset();
			     d.update(x.getBytes());
			     return  d.digest();
			  }
	
	public static String byteArrayToHexString(byte[] b)
	{
	     StringBuffer sb = new StringBuffer(b.length * 2);
	     for (int i = 0; i < b.length; i++){
	       int v = b[i] & 0xff;
	       if (v < 16) {
	         sb.append('0');
	       }
	       sb.append(Integer.toHexString(v));
	     }
	     return sb.toString().toUpperCase();
	  }

	public static void CreateTables()
	{
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(GameMatch.class);
		config.addAnnotatedClass(UserMatchConnection.class);
		config.configure();
		
		new SchemaExport(config).create(true, true);
	}
	
	public static Boolean isTimeToDisableTippFields(String kickOffTimestamp)
	{	Boolean result = false;	
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
			Date parsedDate = dateFormat.parse(kickOffTimestamp);
			
			if (parsedDate.getTime() < (cal.getTimeInMillis()))
			{
				result = true;
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
	
	public static String join(List<String> list, String delim) {

	    StringBuilder sb = new StringBuilder();

	    String loopDelim = "";

	    for(String s : list) {

	        sb.append(loopDelim);
	        sb.append(s);            

	        loopDelim = delim;
	    }

	    return sb.toString();
	}

	public static void fillTopTenTable(Table tableTopTen)
	{
		int index = 1;
		String userIDs = getOnlyNormalUsers();
		Session session = DatabaseHelper.getInstance().getHibernateSession();
		try {
			session.beginTransaction();
			Iterator<Statistic> iter = session.createQuery(
				    "from Statistic where userid in ("+userIDs+") and rank > 0 order by rank asc")
				    .iterate();
			while(iter.hasNext())
			{
				Statistic statistic = iter.next();
				String name = getNameToUserId(statistic.getUserId());
				String points = String.valueOf(statistic.getPoints());
				String rank = String.valueOf(statistic.getRank());
				tableTopTen.addItem(new Object[]{rank, name, points},index);
				index++;
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
	}

	private static String getOnlyNormalUsers()
	{
		List<String> tmpList = new ArrayList<String>();
		String res = "";
		Session session = DatabaseHelper.getInstance().getHibernateSession();
		try {
			session.beginTransaction();
			Iterator<User> iter = session.createQuery(
				    "from User")
				    .iterate();
			
			while(iter.hasNext()) {
				User user = iter.next();
				tmpList.add(String.valueOf(user.getUserID()));
			}
			res =  TipgameUtils.join(tmpList, ",");
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return res;
	}
	
	private static String getNameToUserId(int userId)
	{
		String name = "";
		Session session = DatabaseHelper.getInstance().getHibernateSession();
		session.beginTransaction();
		Iterator<User> iter = session.createQuery(
			    "from User where userId = "+String.valueOf(userId))
			    .iterate();
		while(iter.hasNext())
		{
			User user = iter.next();
			name = user.getChristianname()+" "+user.getName();
		}		
		return name;
	}
	
	public static GameMatch getGameMatchFromId(UserMatchConnection match)
	{
		GameMatch gameMatch = new GameMatch();
		
		Session hibernateSession = DatabaseHelper.getInstance().getHibernateSession();
		hibernateSession.beginTransaction(); 
		DatabaseHelper.getInstance().attachPojoToSession(hibernateSession, match);

		Iterator iter = hibernateSession.createQuery(
			    "from GameMatch where gameMatchId = ?")
			    .setLong(0, match.getGameMatchId())
			    .iterate();
		
		while ( iter.hasNext() ) 
		{
			gameMatch = (GameMatch) iter.next();
		}
		
		hibernateSession.getTransaction().commit();
		
		return gameMatch;
	}
}
