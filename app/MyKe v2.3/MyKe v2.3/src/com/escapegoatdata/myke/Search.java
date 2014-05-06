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

public class Search extends Activity {
	Tools t = new Tools();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_view_screen);
        
        String title = "Search";
        t.out(title);
   

        // MAIN BUTTONS
        ScrollView sv = (ScrollView) findViewById(R.id.sv);
        LinearLayout linlay = t.mkLinearLayout(getApplicationContext(), LinearLayout.VERTICAL);
       
        RelativeLayout row1 = t.mkRelativeLayout(this, RelativeLayout.CENTER_HORIZONTAL);
        row1.setPadding(0, 20, 0, 0);
        RelativeLayout row2 = t.mkRelativeLayout(this, RelativeLayout.CENTER_HORIZONTAL);
        row2.setPadding(0, 20, 0, 350);
       
        ImageButton show = t.mkImageButton(this, R.drawable.tours_shows, Config.mainButtonWidth, Config.mainButtonHeight);
        RelativeLayout.LayoutParams stParams = t.mkLayPar(Data.WRAP,Data.WRAP);        
        stParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        show.setLayoutParams(stParams);
        show.setOnClickListener(searchocl);
        show.setTag(Data.EVENT);
        show.setBackgroundColor(getResources().getColor(R.color.white));
 
        
        ImageButton artist = t.mkImageButton(this, R.drawable.artist_button, Config.mainButtonWidth, Config.mainButtonHeight);
        RelativeLayout.LayoutParams seParams = t.mkLayPar(Data.WRAP,Data.WRAP);
        seParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        artist.setLayoutParams(seParams);
        artist.setOnClickListener(searchocl);
        artist.setTag(Data.ARTIST);
        artist.setBackgroundColor(getResources().getColor(R.color.white));
             
        
        ImageButton venue = t.mkImageButton(this, R.drawable.venue_button, Config.mainButtonWidth, Config.mainButtonHeight);
        RelativeLayout.LayoutParams sfParams = t.mkLayPar(Data.WRAP,Data.WRAP);
        sfParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        venue.setLayoutParams(sfParams);
        venue.setOnClickListener(searchocl);
        venue.setTag(Data.VENUE);
        venue.setBackgroundColor(getResources().getColor(R.color.white));
             
        row1.addView(show);
        row2.addView(artist);
        row2.addView(venue);
        linlay.addView(row1);
        linlay.addView(row2);
        sv.addView(linlay);
        
        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title);     
        Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
        footer.setNet();

	}
	
	
	
	OnClickListener searchocl = new OnClickListener() {
		@Override
		public void onClick(View arg1) {
			
			t.out("CHOICE " + arg1.toString());
			int choice = (Integer) arg1.getTag();
			Intent i = Listeners.getClickIntent(getApplicationContext(), choice);
			startActivity(i);
			
		}
	};
}
