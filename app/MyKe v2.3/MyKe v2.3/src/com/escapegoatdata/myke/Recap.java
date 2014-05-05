package com.escapegoatdata.myke;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class Recap extends Activity {
	Tools t = new Tools();
	int bid;
	Band b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bandpage_screen);
        ScrollView sv = (ScrollView) findViewById(R.id.sv);
  
    
        String bidstr = t.getEx(getIntent(), Data.BID);
        if (bidstr.equals(Data.FILL)) {
        	bid = 0;
        } else {
        	bid = Integer.parseInt(bidstr);
        }
        
        b = Band.getBand(bid);
        
        String title = b.name;
      
        RelativeLayout relay = new RelativeLayout(this);
        RelativeLayout.LayoutParams svP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        relay.setLayoutParams(svP);
        
        
        ImageView bPix =  t.mkImageView(this, b.pix, Config.mainButtonWidth, Config.mainButtonHeight);
        bPix.setId(Data.bandId);
        RelativeLayout.LayoutParams bPa = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        bPa.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bPix.setLayoutParams(bPa);
        bPix.setBackgroundColor(getResources().getColor(R.color.white));
        
        relay.addView(bPix);

        // ADD SET LIST
        Tour tour = (Tour) getFragmentManager().findFragmentById(R.id.tour);
        tour.getSetlist(bid);
        
        
        LinearLayout row1 = t.mkLinearLayout(this, LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams r1Params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        r1Params.addRule(RelativeLayout.BELOW,Data.bandId);
        row1.setLayoutParams(r1Params);
        row1.setId(Data.row1Id);
        
        TextView bethany = new TextView(this);
        bethany.setText("OMG! The show was like really awesome! -- Bethany");
        bethany.setPadding(0, 5, 0, 5);
        row1.addView(bethany);
        TextView danny = new TextView(this);
        danny.setText("I <3 <3 <3 this band! -- Danny");
        danny.setPadding(0, 5, 0, 5);
        row1.addView(danny);
        TextView nim = new TextView(this);
        nim.setText("Worst concert ever :( -- Nim");
        nim.setPadding(0, 5, 0, 25);
        row1.addView(nim);
  
        relay.addView(row1);
        
        TextView setList = new TextView(this);
        setList.setText("Set List");
        setList.setTypeface(null, Typeface.BOLD);
        setList.setGravity(Gravity.CENTER_HORIZONTAL);
        RelativeLayout.LayoutParams slp = t.mkLayPar(Data.MATCH, Data.WRAP);
        slp.addRule(RelativeLayout.BELOW,Data.row1Id);
        setList.setLayoutParams(slp);
        
        
        relay.addView(setList);
        
        
        sv.addView(relay);        
          


	    Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
	    header.setHead(title);     
	    Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
	    footer.setNav(); 
	}
	


	
	OnClickListener ocl = new OnClickListener() {


		@Override
		public void onClick(View arg1) {
			
			t.out("CHOICE " + arg1.toString());
			int choice = (Integer) arg1.getTag();
			
			Intent i = Listeners.getClickIntent(getApplicationContext(), choice); 
			i.putExtra(Data.BID, String.valueOf(b.bid));
			
			startActivity(i);
			
		}
	};
	
	
	
}
