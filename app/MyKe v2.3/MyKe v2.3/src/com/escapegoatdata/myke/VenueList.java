package com.escapegoatdata.myke;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class VenueList extends Activity {
	Tools t = new Tools();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_screen);
        
        String title="Venues";
        t.out(title);
         
        int nVen = Data.venues.length;
        String[] words = new String[nVen];
        Integer[] pix = new Integer[nVen];
        
        for (int j = 0; j<nVen; j++) {
        	words[j] = Data.venues[j].name;
        	pix[j] = Data.venues[j].logo;
        }
        
        ArrayList<String[]> list = t.mkList(nVen,words,pix);
        
		ListView lv = (ListView) findViewById(R.id.lv);
		
		final MyKeAdapter ela = new MyKeAdapter(this,  R.layout.paired_item, list, Data.PAIR);
		
		lv.setAdapter(ela); 
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getApplicationContext(),VenuePage.class);
				i.putExtra(Data.VID, String.valueOf(Data.venues[arg2].vid));
				startActivity(i);
			}
			
		});
		
	    Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
	    header.setHeadSearchBar(title);     
	    Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
	    footer.setNav();   	        

	}
	
}
