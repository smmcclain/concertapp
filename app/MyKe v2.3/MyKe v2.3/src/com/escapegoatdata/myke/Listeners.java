package com.escapegoatdata.myke;

import android.content.Context;
import android.content.Intent;

public class Listeners {

	static Intent getClickIntent(Context ctx, Integer choice) {
		Intent i = 	new Intent(ctx,MainActivity.class);
		
		switch(choice) {
		case Data.SHOW:
			i = new Intent(ctx,Show.class);
			int eid = Data.random.nextInt(Data.nEvents - 1);
			i.putExtra(Data.EID, String.valueOf(eid));
			break;
		case Data.SEARCH:
			i = new Intent(ctx,Search.class);
			break;
		case Data.STUFF:
			i = new Intent(ctx,Stuff.class);
			break;
		case Data.TICKET:
			i = new Intent(ctx,Ticket.class);
			break;		
		case Data.EXCLUSIVE:
			i = new Intent(ctx, Exclusive.class);
			break;
		case Data.DOWNLOADS:
			i = new Intent(ctx, Downloads.class);
			break;
		case Data.STORE:
			i = new Intent(ctx, Store.class);
			break;			
		case Data.ARTIST:
			i = new Intent(ctx, ArtistList.class);
			break;
		case Data.EVENT:
			i = new Intent(ctx, EventList.class);
			break;
		case Data.VENUE:
			i = new Intent(ctx, VenueList.class);
			break;			
		case Data.BUYNOW:
			i = new Intent(ctx, BuyNow.class);
			break;	
		case Data.PICKSEAT:
			i = new Intent(ctx, PickSeat.class);
			break;			
		case Data.MEDIA:
			i = new Intent(ctx, Media.class);
			break;	
		case Data.INFO:
			i = new Intent(ctx, Info.class);
			break;	
		case Data.MYKE:
			i = new Intent(ctx, Myke.class);
			break;	
		default:
			break;
		}
		
		return i;
	}
	
}
