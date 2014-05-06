package com.escapegoatdata.myke;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class Info extends Activity {
	Tools t = new Tools();
	int bid;
	Band b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.scroll_view_screen);
		   
        String bidstr = t.getEx(getIntent(), Data.BID);
        if (bidstr.equals(Data.FILL)) {
        	bid = 0;
        } else {
        	bid = Integer.parseInt(bidstr);
        }
        
        b = Band.getBand(bid);
		
		String title = b.name;
        t.out(title);
        

	    ScrollView sv = (ScrollView) findViewById(R.id.sv);
	    RelativeLayout relay = new RelativeLayout(this);
	    
        ImageView image = t.mkImageView(getApplicationContext(),b.pix,  Config.bandPixWidth  , Config.bandPixHeight);
        RelativeLayout.LayoutParams lp = t.mkLayPar(Data.WRAP  , Data.WRAP);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        image.setLayoutParams(lp);
        image.setId(Data.bandId);
        relay.addView(image);
        
        
        TextView info = new TextView(this);
        RelativeLayout.LayoutParams tPa = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
              tPa.addRule(RelativeLayout.CENTER_HORIZONTAL);
              tPa.addRule(RelativeLayout.BELOW,Data.bandId);
              info.setLayoutParams(tPa);
              info.setText("Lots of Fun Facts about " + b.name);
              info.setTextSize(Config.bannerTextSize);
              relay.addView(info);
        
        
   
        sv.addView(relay);
        
        
        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title);     
        Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
        footer.setNet();
        

	}
	

	
	
}
