package com.escapegoatdata.myke;


import org.json.JSONArray;
import org.json.JSONObject;

import com.escapegoatdata.myke.JSONObjects.Realtor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Contains methods for switching between Activities
 * 
 * @author Daniel Ziskin <info at escapegoatdata.com>
 * @author Sean McClain <sean.mcclain.dc3.sw at gmail.com>
 */
public class Listeners {

    /** Stores a calling Activity (usually the MainActivity) */
    private static Activity context = null;
    public static Activity getContext() {
        return context;
    }
    public static void setContext(Activity newContext) {
        context = newContext;
    }

	/**
	 * Retrieves a configured Intent which allows a user to switch Activities
	 * from the MainActivity to a button selected Activity. For example,
	 * pushing the "show" button from the main screen will set up the Intent
	 * for loading the Show Activity.
	 *
	 * @see http://developer.android.com/reference/android/content/Intent.html
	 * @param ctx main screen's context
	 * @param choice button selection
	 * @return configured Intent
	 */
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

    /**
     * Handles data retrieved by URLGrabber.
     *
     * Consider this the main entry point to the program.
     */
    public static class URLGrabberHandler extends Handler {

        /**
         * "Main" method
         */
        public void handleMessage(Message msg)
        {
            JSONObject root;
            JSONArray json_realtors;
            int i;
            Realtor newRealtor = new Realtor();

            /* load retrieved Realtor data into a list */
            try {
                root = new JSONObject((String) msg.obj);
                json_realtors = (JSONArray) root.getJSONArray("realtors");
                for (i = 0; i < json_realtors.length(); i++) {
                    newRealtor.setData(json_realtors.getJSONObject(i));
                    Toast.makeText(context, newRealtor.toString(), 1).show();
                }
            } catch (Exception e) {
                return;
            }
        }
    }
}
