package com.tipgame.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import com.tipgame.data.GameMatch;
import com.tipgame.data.User;
import com.tipgame.data.UserMatchConnection;

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
}
