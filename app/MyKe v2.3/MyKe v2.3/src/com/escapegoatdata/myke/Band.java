package com.escapegoatdata.myke;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;

public class Band {
	String name;
	int pix;
	URL website;
	Date[] when;
	Integer[] where;
	int bid;
	    	
	public Band(String bName, Integer bPix, String web, int inBid) {
				String host = "en.wikipedia.org";
				String prot = "http";
	    		name = bName;
	    		pix = bPix;
	    		try {
					website = new URL(prot, host, "/wiki/" + web);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		when = new Date[1];
	    		where = new Integer[1];
	    		when[0] = Date.valueOf("2014-08-30");
	    		where[0] = 2; 
	    		bid = inBid;
	    	}
	
	static public void load() {
		Data.bands[0] = new Band("The Monkees", R.drawable.monkees,"Monkees", 0 );
		Data.bands[1] = new Band("Miles Davis", R.drawable.milesdavis,"Miles_Davis",1 );
		Data.bands[2] = new Band("Led Zeppelin",R.drawable.ledzeppelin,"Led_Zeppelin",2 );
		Data.bands[3] = new Band("Madonnna", R.drawable.madonna,"Madonna_%28entertainer%29",3 );
		Data.bands[4] = new Band("The Beatles",R.drawable.thebeatles,"The_Beatles",4 );
		Data.bands[5] = new Band("Lady Gaga",R.drawable.ladygaga,"Lady_Gaga",5 );
		Data.bands[6] = new Band("Grateful Dead",R.drawable.gratefuldead,"The_Grateful_Dead",6 );
		Data.bands[7] = new Band("Bob Dylan", R.drawable.bobdylan,"Bob_Dylan",7 );
		Data.bands[8] = new Band("Bob Marley",R.drawable.bobmarley,"Bob_Marley",8 );
		Data.bands[9] = new Band("Lorde",R.drawable.lorde,"Lorde",9 );
	}
	
	static public Band getBand(int bid) {
		Band b = Data.bands[0]; // default
		for(int i=0; i<Data.nBands;i++) {
			if(bid == Data.bands[i].bid) {
				b = Data.bands[i];
			}
		}
		return b;
	}
	
}

