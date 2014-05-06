package com.escapegoatdata.myke;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ArtistList extends Activity {
	Tools t = new Tools();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.list_view_screen);
        
        String title="Artists";
        t.out(title);
         

        int nArt = Data.bands.length; 
        String[] words = new String[nArt + 1]; // add an extra one so the last entry isn't covered by the footer
        Integer[] pix = new Integer[nArt + 1];
        
        
        for (int j = 0; j<nArt; j++) {
        	words[j] = Data.bands[j].name;
        	pix[j] = Data.bands[j].pix;
        }
        // NOTHING AT THE BOTTOM
        words[nArt] = "";
        pix[nArt] = R.drawable.blank;
        
        ArrayList<String[]> list = t.mkList(nArt + 1,words,pix);
        
        
		ListView lv = (ListView) findViewById(R.id.lv);
		
		final MyKeAdapter ela = new MyKeAdapter(this,  R.layout.paired_item, list, Data.PAIR);
		
		lv.setAdapter(ela); 
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getApplicationContext(),BandPage.class);
				i.putExtra(Data.BID, String.valueOf(Data.bands[arg2].bid));
				startActivity(i);
			}
			
		});
		
	    Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
	    header.setHeadSearchBar(title);     
	    Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
	    footer.setNav();   	
        

	}

	
	
}
