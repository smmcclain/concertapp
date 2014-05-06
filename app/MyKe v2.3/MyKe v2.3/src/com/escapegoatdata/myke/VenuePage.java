package com.escapegoatdata.myke;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class VenuePage extends Activity {
	Tools t = new Tools();
	int vid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blank_view_screen);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.content);
        
        String vidstr = t.getEx(getIntent(),Data.VID);
        if (vidstr.equals(Data.FILL)) {
        	vid = 0;
        } else {
        	vid = Integer.valueOf(vidstr);
        }
        
        Venue v = Venue.getVenue(vid);
        
        String title= v.name;
        
        ImageView bPix =  t.mkImageView(this, v.logo, Config.bandPixWidth, Config.bandPixHeight);
        bPix.setId(Data.bandId);
        RelativeLayout.LayoutParams bPa = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        bPa.addRule(RelativeLayout.BELOW,Data.headerId);
        bPa.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bPix.setLayoutParams(bPa);
        
        rl.addView(bPix);
        
        LinearLayout ll = t.mkLinearLayout(this, LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams llP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        llP.addRule(RelativeLayout.BELOW,Data.bandId);
        ll.setLayoutParams(llP);
        
        
        TextView addr = new TextView(this);
        addr.setText(v.address);
        addr.setTextSize(Config.pairNameSize);
        
        TextView city = new TextView(this);
        city.setText(v.city);
        city.setTextSize(Config.pairNameSize);
        
        TextView phone = new TextView(this);
        phone.setText(v.phone);
        phone.setTextSize(Config.pairNameSize);
        
        ll.addView(addr);
        ll.addView(city);
        ll.addView(phone);
        
        rl.addView(ll);
        

        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title);     
        Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
        footer.setNet();
        
	}
	


	
	
}
