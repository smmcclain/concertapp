package com.escapegoatdata.myke;

import java.sql.Date;

public class Event {
	String name;
	Integer[] lineup;
	String[] showtime;
	String[] status;
	int nActs;
	Date[] when;
	Integer[] where;
	int logo;
	int eid;
	
	
	public Event(String what, Integer[] who, String[] times, Date[] dates, Integer[] place, int pic, int id) {
		name = what;
		lineup = who;
		showtime = times;
		// requires when to be of the format yyyy-mm-dd
		nActs = who.length;
		logo = pic;
		eid = id;
		when = dates;
		where = place;
	
	}
	
	public static void load() {

		// an array of band ids
		Integer[] lineupId = { 0,1,5,3,4 };
		String[] times = { "NOON","4:23","7:45","11:11","MIDNIGHT" };
		Integer[] vid = { 0,1,2,3 };
		Date[] dates = {Date.valueOf("2014-07-04"), Date.valueOf("2014-07-07"),Date.valueOf("2014-07-24"), Date.valueOf("2014-08-08")};
		Data.events[0] = new Event("Shakey Boom Tour",lineupId, times, dates,vid, R.drawable.shakeyboom, 0);
		
		lineupId = new Integer[] { 7,6,5,8,9 };
		vid = new Integer[] { 1,3,0,2 };
		dates = new Date[] {Date.valueOf("2014-07-11"), Date.valueOf("2014-07-17"),Date.valueOf("2014-07-21"), Date.valueOf("2014-07-28")};
		times = new String[] {"8","9:15","10:30","11:30","MIDNIGHT" };
		Data.events[1] = new Event("Noise Fest",lineupId, times, dates, vid, R.drawable.noise_fest, 1);
		
		lineupId = new Integer[] { 8,6,5,2,9 };
		times = new String[] {"8","9:15","10:30","11:30","MIDNIGHT" };
		vid = new Integer[] { 3,0,1,2 };
		dates = new Date[] {Date.valueOf("2014-07-22"), Date.valueOf("2014-07-30"),Date.valueOf("2014-08-04"), Date.valueOf("2014-08-20")};
		Data.events[2] = new Event("Fresh Fest",lineupId, times,dates,vid, R.drawable.fresh_fest, 2);
		

		lineupId = new Integer[] { 6,0,5,9,1 };
		times = new String[] {"8","9:15","10:30","11:30","MIDNIGHT" };
		vid = new Integer[] { 2,3,1,0 };
		dates = new Date[] {Date.valueOf("2014-07-29"), Date.valueOf("2014-08-07"),Date.valueOf("2014-08-13"), Date.valueOf("2014-09-02")};
		Data.events[3] = new Event("Rebel Culture",lineupId, times, dates,vid, R.drawable.rebel_culture, 3);

	}
	
	
	// RETURN AN EVENT THAT MATCHES THE EID
	static public Event getEvent(int eid) {
		Event e = Data.events[0]; // default
		for(int i=0; i<Data.nEvents;i++) {
			if (Data.events[i].eid == eid ) {
				e = Data.events[i];
			}
		}
		
		return e;
	}

	
}
