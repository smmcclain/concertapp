package com.escapegoatdata.myke;

import java.sql.Date;
import java.util.Arrays;

public class Concert {
	int bid;
	int vid;
	String time;
	Date date;
	Tools t = new Tools();
	
	Concert(int inBid, int inVid, String inTime, Date inDate) {
		bid = inBid;
		vid = inVid;
		time = inTime;
		date = inDate;
	}
	
	public Concert[] mkTour(int bid) {
		int count = 0; 
		Concert[] roadTrip = null;
		Concert[] roadTripTMP = null;
		Event e;
		
		// COUNT HOW MANY CONCERTS THERE ARE
		for(int i = 0; i < Data.nEvents; i++) {
			e = Data.events[i];
			Integer[] lup = e.lineup;
			for(int j = 0; j < lup.length; j++) {
				if (lup[j] == bid) {
					count = count + e.when.length; // if you're in the lineup then each event date is a gig
				}
			}
		}
		
		
		
		int k = 0;
		if (count > 0) {
			roadTripTMP = new Concert[count];
			for(int i = 0; i < Data.nEvents; i++) {
				e = Data.events[i];
				Integer[] lup = e.lineup;
				for(int j = 0; j < lup.length; j++) {
					if (lup[j] == bid) {
						for (int l = 0; l < e.when.length; l++) {
							t.out("CONCERT VID " + e.where[l]);
							roadTripTMP[k] = new Concert(bid, Integer.valueOf(e.where[l]),  e.showtime[l], e.when[l]);
							k++;
						}
					}
				}
			}
		
			
			// SORT BY CONCERT DATE
			
			Long[] dateArr = new Long[count];
			roadTrip = new Concert[count];
			for (int i= 0; i < count; i++) {
				dateArr[i] = roadTripTMP[i].date.getTime();
			}
			
			Arrays.sort(dateArr);
			k = 0;
			
			// oh boy this is gonna crash if the band is playing in two events at the same time...
			for (int i = 0; i < count; i++) {
				for (int j = 0; j < count; j++) {
					if (roadTripTMP[j].date.getTime() == dateArr[i]) {
						roadTrip[k] = roadTripTMP[j];
						k++;
					}
				}
			}	
		}
		
		return roadTrip;
	}

}
