package com.escapegoatdata.myke;

import java.net.MalformedURLException;
import java.net.URL;

public class Venue {
	String name;
	Integer logo;
	URL website;
	String address;
	String phone;
	String city;
	int vid;
	int seatMap;
	
	    	
	public Venue(String bName, int bPix, String web, String addy, String phony, String metro, int inVid, int map) {
		name = bName;
		logo = bPix;
		try {
			website = new URL(web);
		} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
			e.printStackTrace();
		}
		address = addy;
		phone = phony;
		city = metro;
		vid = inVid;
		seatMap = map;
	}
	
	static public void load() {
		Data.venues[0] = new Venue("The Spectrum", R.drawable.spectrum,"http://en.wikipedia.org/wiki/Spectrum_%28arena%29","3601 South Broad Street","202-123-4566","Philadelphia",0,R.drawable.seatmap);
		Data.venues[1] = new Venue("Madison Square Garden", R.drawable.garden , "http://thegarden.com" ,"4 Pennsylvania Plaza", "212-567-3452", "New York City", 1, R.drawable.gardenseats);
		Data.venues[2] = new Venue("The Pepsi Center", R.drawable.pepsi , "http://pepsicenter.com" ,"1000 Chopper Cir", "212-567-3452", "Denver", 2, R.drawable.gardenseats);
		Data.venues[3] = new Venue("Hogwarts Academy", R.drawable.hogwarts , "http://harrypotter.wikia.com/wiki/Hogwarts_School_of_Witchcraft_and_Wizardry" ,"222 Diagon Alley", "111-111-2222", "London", 3, R.drawable.hogwartsseats);
	}
	
	static public Venue getVenue(int vid) {
		Venue v = Data.venues[0]; // default

		for(int i=0; i<Data.nVenues;i++) {
			if(vid == Data.venues[i].vid) {
				v = Data.venues[i];
			}
		}
		
		return v;
	}
	
}

