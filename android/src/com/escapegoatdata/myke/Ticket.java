package com.escapegoatdata.myke;

import java.sql.Date;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Ticket extends Activity {
	Tools t = new Tools();
	int eid;
	Concert[] c;
	int nseq;
	int nSeats;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blank_view_screen);

		String title = "Tickets";
        t.out(title);
         
        RelativeLayout sv = (RelativeLayout) findViewById(R.id.content);
        
        
        // JUST SETTING THE EVENT FOR THIS FIXED VALUE
        eid = 0;
        Event e = Event.getEvent(eid);
        nseq = 0;
        Venue v = Venue.getVenue(e.where[nseq]);
        Date d = e.when[nseq];
        
        Intent inTent = getIntent();
   //     String seatnum = t.getEx(inTent, Data.SEAT);
        
		/* PLACE AND TIME */
		ImageButton ib = t.mkImageButton(this, v.logo, Config.showButtonWidth, Config.showButtonHeight);
		ib.setBackgroundColor(getResources().getColor(R.color.white));
				
		TextView tv = new TextView(this);
		tv.setText(d.toString());
		tv.setTextSize(Config.pairNameSize);

		LinearLayout linlay = t.mkLinearLayout(this, LinearLayout.VERTICAL);
		linlay.addView(ib);
		linlay.addView(tv);
		linlay.setId(Data.linId);
		sv.addView(linlay);
		
		/* DONE WITH PLACE AND TIME */
		
		
		/* HOW MANY SEATS */

		String howmany = t.getEx(inTent, Data.NSEAT);
		if (howmany.equals(Data.FILL)) {

			LinearLayout spinlay = new LinearLayout(this);
			RelativeLayout.LayoutParams slParams = new RelativeLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			// slParams.addRule(RelativeLayout.BELOW, R.id.place);
			slParams.addRule(RelativeLayout.BELOW,Data.linId);
			spinlay.setLayoutParams(slParams);
			spinlay.setOrientation(LinearLayout.HORIZONTAL); // horizontal
			spinlay.setId(Data.slId);

			TextView hml = new TextView(this);
			RelativeLayout.LayoutParams hmlParams = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			hml.setLayoutParams(hmlParams);
			hml.setText("How Many Tickets?: ");
			hml.setTextColor(getResources().getColor(R.color.black));
			hml.setTextSize(25);
			hml.setTypeface(Typeface.SANS_SERIF);

			Spinner spinner = new Spinner(this);
			// Create an ArrayAdapter using the string array and a default
			// spinner layout
			ArrayAdapter<CharSequence> adapter = ArrayAdapter
					.createFromResource(this, R.array.howmany,
							android.R.layout.simple_spinner_item);
			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			spinner.setAdapter(adapter);
			spinner.setSelection(1); // default 2 tix
			spinner.setOnItemSelectedListener(seatListener);

			ImageButton seat = new ImageButton(this);

			RelativeLayout.LayoutParams siParams = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			siParams.addRule(RelativeLayout.BELOW, Data.slId);
			siParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		//	siParams.setMargins(0, 10, 0, 0);
			seat.setLayoutParams(siParams);
			seat.setImageResource(R.drawable.pickseat);
			seat.setAdjustViewBounds(true);
			seat.setMaxHeight(Config.searchButtonHeight);
			seat.setScaleType(ScaleType.FIT_XY);
			seat.setTag(Data.PICKSEAT);
			seat.setBackgroundColor(getResources().getColor(R.color.white));
			seat.setOnClickListener(ocl);

			spinlay.addView(hml);
			spinlay.addView(spinner);
			sv.addView(spinlay);
			sv.addView(seat);

		} else {

			t.out("SEATS SELECTED " + howmany);
			
			LinearLayout res = t.mkLinearLayout(this, LinearLayout.VERTICAL);
			RelativeLayout.LayoutParams reParama = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
			reParama.addRule(RelativeLayout.BELOW, Data.linId);
			res.setLayoutParams(reParama);
			
			

			nSeats = Integer.parseInt(howmany);
			ArrayList<String[]> tix = new ArrayList<String[]>(nSeats);

			for (int j = 0; j < nSeats; j++) {
				tix.add(new String[] { "Section 1 Row A Seat "
						+ String.valueOf(42 + j) });
			}

			ListView lv = new ListView(this);
			lv.setId(Data.seliId);	

			final MyKeAdapter ela = new MyKeAdapter(this,R.layout.seating_item,tix,Data.TICKET);

			lv.setAdapter(ela);

			TextView total = new TextView(this);
			total.setText("TOTAL $" + nSeats * Config.ticketPrice);
			total.setTextColor(getResources().getColor(R.color.red));
			total.setGravity(Gravity.RIGHT);
			total.setTextSize(20);
			total.setTypeface(null, Typeface.BOLD);
			
			res.addView(lv);
			
			res.addView(total);

			ImageButton buynow = new ImageButton(this);
			buynow.setImageResource(R.drawable.buynow);
			buynow.setOnClickListener(ocl);
			buynow.setAdjustViewBounds(true);
			buynow.setScaleType(ScaleType.FIT_XY);
			buynow.setMaxHeight(Config.buyNowHeight);
			buynow.setTag(Data.BUYNOW);
			buynow.setBackgroundColor(getResources().getColor(R.color.white));
			res.addView(buynow);
			
			sv.addView(res);
		}

        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title);     
 //       Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
 //       footer.setNet();
	
		
	}


	OnItemSelectedListener seatListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			nSeats = arg2 + 1;

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {

			nSeats = 2;

		}

	};

	OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			
			t.out("CHOICE " + arg0.toString());
			int choice = (Integer) arg0.getTag();
			Intent i = Listeners.getClickIntent(getApplicationContext(), choice);
			i.putExtra(Data.NSEAT, String.valueOf(nSeats));
			startActivity(i);
		}

	};
	
	
	
	
}
