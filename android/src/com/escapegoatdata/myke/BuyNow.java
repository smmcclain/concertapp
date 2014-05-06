package com.escapegoatdata.myke;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class BuyNow extends Activity {
	Tools t = new Tools();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blank_view_screen);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.content);
    
        String title = "Buy Now";
        t.out(title);
        
 
    	ImageButton ib = t.mkImageButton(this, R.drawable.paypal, 0, Config.hugeButtonHeight);
    	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
    	params.addRule(RelativeLayout.CENTER_HORIZONTAL);
    	ib.setLayoutParams(params);
    	ib.setBackgroundColor(getResources().getColor(R.color.white));
		rl.addView(ib);
		
        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title);     
 //       Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
 //       footer.setNet();
  
        

	
	}
}
