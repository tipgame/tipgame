package com.tipgame.Threads;

import java.io.Serializable;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.ui.TextField;

public class DateCheckThread extends Thread implements Serializable{
	
	private static final long serialVersionUID = 6969871601928939400L;
	private TextField t1;
	private TextField t2;
	private String kickOffTimestamp;
	
	public DateCheckThread(TextField t1, TextField t2, String timestamp)
	{		
		this.kickOffTimestamp = timestamp.replace("Uhr", "").trim();
		this.t1 = t1;
		this.t2 = t2;
	}
	
	@Override
    public void run() {
        try {
        	while(true) {
	        	if (TipgameUtils.compareDates(kickOffTimestamp))
	           	{
	           		t1.setEnabled(false);
	                t2.setEnabled(false);
	                this.interrupt();
	           	}
	            Thread.sleep(120000);
        	}
        } catch (InterruptedException e) {
        }

       	
    }	
}
