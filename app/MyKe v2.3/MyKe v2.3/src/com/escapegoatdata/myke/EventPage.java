package com.escapegoatdata.myke;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventPage extends Activity {
	Tools t = new Tools();
	int eid;
	Event e;
	int nActs;
	ImageButton[] ib;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventpage_screen);
        RelativeLayout relay = (RelativeLayout) findViewById(R.id.relay);
    
        t.out("EVENT PAGE");
        
        String eidstr = t.getEx(getIntent(), Data.EID);
        
        if (eidstr.equals(Data.FILL)) {
        	Data.random.nextInt(Data.nEvents );
        } else {
        	eid = Integer.parseInt(eidstr);
        }
        
        e = Event.getEvent(eid);
        
        String title = e.name;
        
        
        nActs = e.lineup.length;

		ib = new ImageButton[nActs];
		LinearLayout[] bInfo = new LinearLayout[nActs];
		TextView[] bName = new TextView[nActs];

        HorizontalScrollView hsv = new HorizontalScrollView(this);
        RelativeLayout.LayoutParams hsvParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        hsvParams.addRule(RelativeLayout.BELOW,R.id.header);
        hsv.setLayoutParams(hsvParams);
        hsv.setId(Data.horScroId);
		
		LinearLayout acts = t.mkLinearLayout(this, LinearLayout.HORIZONTAL); 

	    for (int iBand = 0; iBand < nActs; iBand++) {
	    	bInfo[iBand] = new LinearLayout(this);
	    	bInfo[iBand].setOrientation(1); // vertical
	    	bInfo[iBand].setLayoutParams(new LayoutParams(150,180));
	    	ib[iBand] = new ImageButton(this);
	    
	    	Band b = Band.getBand(e.lineup[iBand]);
	    
	    	bName[iBand] = new TextView(this);
	    	bName[iBand].setText(b.name);
	    	bInfo[iBand].addView(bName[iBand]);

	    	Drawable replacer = getResources().getDrawable(b.pix);
	    	ib[iBand].setImageDrawable(replacer);
	    	ib[iBand].setContentDescription(b.name);
	    	ib[iBand].setPadding(5, 5, 5, 5);
	    	ib[iBand].setScaleType(ScaleType.FIT_CENTER);
	    	ib[iBand].setOnClickListener(bcl);
	    	ib[iBand].setMaxHeight(50);
	    	ib[iBand].setBackgroundColor(getResources().getColor(R.color.white));
	    	
	    	bInfo[iBand].addView(ib[iBand]);
	    		    	
	    	acts.addView(bInfo[iBand]);
	    }

	    hsv.addView(acts);
	    relay.addView(hsv);
	    
	    
        ImageView ePix =  t.mkImageView(this, e.logo, Config.mainButtonWidth, Config.mainButtonHeight);
        ePix.setId(Data.bandId);
        RelativeLayout.LayoutParams bPa = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        bPa.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bPa.addRule(RelativeLayout.BELOW, Data.horScroId);
        ePix.setLayoutParams(bPa);
        ePix.setBackgroundColor(getResources().getColor(R.color.white));
        ePix.setPadding(0, 10, 0, 0);
        
        relay.addView(ePix);
	    
        // ADD TOUR DATES
        Tour tour = (Tour) getFragmentManager().findFragmentById(R.id.tour);
        // NEED TO MAKE A FUNCTION THAT SHOWS TOUR DATES BASED ON eid
        tour.tourDates(8);
	    
        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title);     
        Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
        footer.setTix();
      
	}
	
	

	OnClickListener bcl = new OnClickListener()  {
		
		@Override
		public void onClick(View view) {
			t.out( "BAND BUTTON PUSHED"); 
			Intent who = new Intent(getApplicationContext(), BandPage.class);
			
			for(int i=0; i<nActs;i++) {
				if (view == ib[i]) {
					t.out("i = " + i);
					String bid = String.valueOf(Data.events[e.eid].lineup[i]);		
					who.putExtra(Data.BID, bid); 
					startActivity(who);
				}
			}
		}
	};
	
}
