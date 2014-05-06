package com.escapegoatdata.myke;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class Exclusive extends Activity {
	Tools t = new Tools();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_view_screen);
		
	    String title = "Exclusive MyKe";
	    t.out(title);
	
	    ScrollView sv = (ScrollView) findViewById(R.id.sv);
	    RelativeLayout relay = new RelativeLayout(this);
	    
        ImageView image = t.mkImageView(getApplicationContext(), R.drawable.rocky_horror,  Config.simpleImageWidth  , Config.simpleImageHeight);
        RelativeLayout.LayoutParams lp = t.mkLayPar(Data.WRAP  , Data.WRAP);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        image.setLayoutParams(lp);
        relay.addView(image);
   
        sv.addView(relay);
   
        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title);     
        Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
        footer.setNet();
      
	}
	
	
}
