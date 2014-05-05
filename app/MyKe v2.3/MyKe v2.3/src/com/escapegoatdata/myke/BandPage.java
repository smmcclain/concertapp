package com.escapegoatdata.myke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;

public class BandPage extends Activity {
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
        
        
        ImageView bPix =  t.mkImageView(this, b.pix, Config.bandPixWidth, Config.bandPixHeight);
        bPix.setId(Data.bandId);
        RelativeLayout.LayoutParams bPa = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        bPa.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bPix.setLayoutParams(bPa);
        bPix.setBackgroundColor(getResources().getColor(R.color.white));
        
        relay.addView(bPix);
       
        
        LinearLayout row1 = t.mkLinearLayout(this, LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams r1Params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        r1Params.addRule(RelativeLayout.BELOW,Data.bandId);
        row1.setLayoutParams(r1Params);
        row1.setId(Data.row1Id);
        
        
        ImageButton media = t.mkImageButton(this, R.drawable.media_button, Config.bandButtonWidth, Config.bandButtonHeight);
        media.setBackgroundColor(getResources().getColor(R.color.white));
        media.setTag(Data.MEDIA);
        media.setOnClickListener(ocl);
        
        
        ImageButton info = t.mkImageButton(this, R.drawable.info_button, Config.bandButtonWidth, Config.bandButtonHeight);
        info.setBackgroundColor(getResources().getColor(R.color.white));
        info.setTag(Data.INFO);
        info.setOnClickListener(ocl);
        
        
        ImageButton down = t.mkImageButton(this, R.drawable.download_music, Config.bandButtonWidth, Config.bandButtonHeight);
        down.setBackgroundColor(getResources().getColor(R.color.white));
        down.setTag(Data.DOWNLOADS);
        down.setOnClickListener(ocl);
        
        ImageButton stuff = t.mkImageButton(this, R.drawable.mouse, Config.bandButtonWidth, Config.bandButtonHeight);
        stuff.setBackgroundColor(getResources().getColor(R.color.white));
        stuff.setTag(Data.STUFF);
        stuff.setOnClickListener(ocl);
        
        row1.addView(info);
        row1.addView(down);
        row1.addView(stuff);
        row1.addView(media);
        relay.addView(row1);
        
        sv.addView(relay);        
          
        // ADD TOUR DATES
        Tour tour = (Tour) getFragmentManager().findFragmentById(R.id.tour);
        tour.tourDates(bid);

	    Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
	    header.setHead(title);     
	    Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
	    footer.setTix(); 
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
