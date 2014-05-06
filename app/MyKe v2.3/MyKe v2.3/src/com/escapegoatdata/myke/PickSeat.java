package com.escapegoatdata.myke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class PickSeat extends Activity {
	Tools t = new Tools();
	String eid;
	String bid;
	String eseq;
	String nSeat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blank_view_screen);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.content);
    
        String title = "Pick Seats";
        t.out(title);
        
        
        
    	Intent i = getIntent();
    	eid = t.getEx(i, Data.EID);
    	bid = t.getEx(i, Data.BID);
   // 	eseq = t.getEx(i, Data.ESEQ);
    	nSeat = t.getEx(i, Data.NSEAT);
    	
  
    	ImageButton ib = t.mkImageButton(this, R.drawable.seatmap, 0, Config.hugeButtonHeight);
    	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
    	params.addRule(RelativeLayout.CENTER_HORIZONTAL);
    	ib.setLayoutParams(params);
    	ib.setBackgroundColor(getResources().getColor(R.color.white));
    	
    	ib.setOnClickListener(ocl);
		rl.addView(ib);
		
        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title);     
 //       Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
 //       footer.setNet();
  
    
	}
	

OnClickListener ocl = new OnClickListener() {

	@Override
	public void onClick(View arg0) {
		Intent oi = new Intent(getApplicationContext(),Ticket.class);
		oi.putExtra(Data.SEAT, "A37");
		oi.putExtra(Data.BID, bid);
		oi.putExtra(Data.EID, eid);
	//	oi.putExtra(Data.ESEQ, eseq);
		oi.putExtra(Data.NSEAT, nSeat);
		startActivity(oi);
	}
	
};
	
	
}
