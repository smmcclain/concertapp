package com.escapegoatdata.myke;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Tracks extends Activity {
	Tools t = new Tools();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_screen);
        
        String title="Tracks";
        t.out(title);
        
        
        int nTracks = 4;
        String[] words = new String[] {"Vocals", "Guitar", "Bass", "Drums"};
        Integer[] pix = new Integer[] {R.drawable.vocals, R.drawable.guitar, R.drawable.bass, R.drawable.drums }; 
        
        ArrayList<String[]> list = t.mkList(nTracks,words,pix);
        
		ListView lv = (ListView) findViewById(R.id.lv);
	
		final MyKeAdapter ela = new MyKeAdapter(this,  R.layout.track_item, list, Data.TRACKS);
		
		lv.setAdapter(ela);      
        
        
	    Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
	    header.setHead(title);     
	    Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
	    footer.setNet();   
	}
	
}
