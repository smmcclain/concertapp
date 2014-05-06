package com.escapegoatdata.myke;

import java.util.Random;

import android.view.ViewGroup.LayoutParams;

public interface Data {

	// Useful constants
	public final String FILL = "Unknown";
	public final int nTeasers = 10;
	public final int nEvents = 4;
	public final int nBands = 10;
	public final int nVenues = 4;
	public final int nSongs = 6;
	
	// DATA ARRAYS
	public final String[] teasers = new String[nTeasers]; 	// an array of social media teasers
	public final Event[] events = new Event[nEvents]; 	// an array of events
	public final Band[] bands = new Band[nBands]; 	// an array of bands
	public final Venue[] venues = new Venue[nVenues]; // an array of venues
	
	// Useless constants
	public final int lineId = 12345;
	public final int nwbId = 23451;
	public final int headerId = 34512;
	public final int hsvId = 45123;
	public final int horScroId = 51234;
	public final Random random = new Random();
	public final int plabaId = 12354;
	public final int bandId = 12534;
	public final int row1Id = 15234;
	public final int row2Id = 512346;
	public final int slId = 512364;
	public final int linId = 512634;
	public final int seliId = 516234;
	public final int totId = 561234;
	public final int r0Id = 456123;
	public final int WRAP = LayoutParams.WRAP_CONTENT;
	public final int MATCH = LayoutParams.MATCH_PARENT;

	// PASS THESE EXTRAS
	public final String EID = "com.escapegoatdata.myke.EID"; // EVENT ID
	public final String BID = "com.escapegoatdata.myke.BID"; // BAND ID
	public final String VID = "com.escapegoatdata.myke.VID"; // VENUE ID
	public final String AV = "com.escapegoatdata.myke.AV";  // AUDIO OR VIDEO
	public final String DATE = "com.escapegoatdata.myke.DATE"; // DATE OF CONCERT
	public final String SEAT = "com.escapegoatdata.myke.SEAT"; // PASSING SEAT INFO
	public final String NSEAT = "com.escapegoatdata.myke.NSEAT"; // PASSING SEAT INFO
	public final String CAMERA = "com.escapegoatdata.myke.CAMERA"; // PASSING CAMERA INFO
	public final String CAMNUM = "com.escapegoatdata.myke.CAMNUM"; // PASSING CAMERA INFO
	
	// for the generic adapter
	public final int HOME = 0;
	public final int EVENT = 1;
	public final int ARTIST = 2;
	public final int TOUR = 3;
	public final int TICKET = 4;
	public final int SHOW = 5;
	public final int TRACKS = 6;
	public final int VENUE = 7;
	public final int SEARCH = 8;
	public final int STUFF = 9;
	public final int MEDIA = 10;
	public final int INFO = 11;
	public final int DOWNLOADS = 12;
	public final int EXCLUSIVE = 13;
	public final int STORE = 14;
	public final int PAIR = 15;
	public final int PICKSEAT = 16;
	public final int BUYNOW = 17;
	public final int MYKE = 18;
	
	
	// FOR PLAYBACK
	public final String AUDIO = "audio";
	public final String VIDEO = "video";

}
