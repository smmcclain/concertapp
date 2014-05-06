package com.escapegoatdata.myke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class Stuff extends Activity {
	Tools t = new Tools();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_view_screen);
		
	    String title = "Stuff";
	    t.out(title);
	

          
        ScrollView sv = (ScrollView) findViewById(R.id.sv);
        LinearLayout linlay = t.mkLinearLayout(getApplicationContext(), LinearLayout.VERTICAL);

       
        RelativeLayout row1 = t.mkRelativeLayout(this, RelativeLayout.CENTER_HORIZONTAL);
        RelativeLayout row2 = t.mkRelativeLayout(this, RelativeLayout.CENTER_HORIZONTAL);
        row2.setPadding(0, 10, 0, 340);
       
        ImageButton exclusive = t.mkImageButton(this, R.drawable.exclusive, Config.mainButtonWidth, Config.mainButtonHeight);
        RelativeLayout.LayoutParams stParams = t.mkLayPar(Data.WRAP,Data.WRAP);        
        stParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        exclusive.setLayoutParams(stParams);
        exclusive.setOnClickListener(ocl);
        exclusive.setTag(Data.EXCLUSIVE);
        exclusive.setBackgroundColor(getResources().getColor(R.color.white));

        
        ImageButton download = t.mkImageButton(this, R.drawable.downloads, Config.mainButtonWidth, Config.mainButtonHeight);
        RelativeLayout.LayoutParams seParams = t.mkLayPar(Data.WRAP,Data.WRAP);
        seParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        download.setLayoutParams(seParams);
        download.setOnClickListener(ocl);
        download.setTag(Data.DOWNLOADS);
        download.setBackgroundColor(getResources().getColor(R.color.white));
             
        
        ImageButton store = t.mkImageButton(this, R.drawable.store_button, Config.mainButtonWidth, Config.mainButtonHeight);
        RelativeLayout.LayoutParams sfParams = t.mkLayPar(Data.WRAP,Data.WRAP);
        sfParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        store.setLayoutParams(sfParams);
        store.setOnClickListener(ocl);
        store.setTag(Data.STORE);
        store.setBackgroundColor(getResources().getColor(R.color.white));
             
        row1.addView(exclusive);
        row2.addView(download);
        row2.addView(store);
        linlay.addView(row1);
        linlay.addView(row2);
        sv.addView(linlay);
    

        
        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title);      
        Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
        footer.setNet();
      
	}
    

OnClickListener ocl = new OnClickListener() {

	@Override
	public void onClick(View arg0) {
		
		t.out("CHOICE " + arg0.toString());
		Intent i = Listeners.getClickIntent(getApplicationContext(), (Integer) arg0.getTag());
		startActivity(i);
		
	}
};

	
	
}
