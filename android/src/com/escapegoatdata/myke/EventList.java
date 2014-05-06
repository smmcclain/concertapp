package com.escapegoatdata.myke;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class EventList extends Activity {
	Tools t = new Tools();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_screen);
        
        String title="Shows & Tours";
        t.out(title);
         
        
        int nEven = Data.events.length;
        String[] words = new String[nEven];
        Integer[] pix = new Integer[nEven];
        
        for (int j = 0; j<nEven; j++) {
        	words[j] = Data.events[j].name;
        	pix[j] = Data.events[j].logo;
        }
        
        ArrayList<String[]> list = t.mkList(nEven,words,pix);
        
		ListView lv = (ListView) findViewById(R.id.lv);
		
		final MyKeAdapter ela = new MyKeAdapter(this,  R.layout.paired_item, list, Data.PAIR);
		
		lv.setAdapter(ela); 
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getApplicationContext(),EventPage.class);
				i.putExtra(Data.EID, String.valueOf(Data.events[arg2].eid));
				startActivity(i);
			}
			
		});
		
	    Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
	    header.setHead(title);     
	    Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
	    footer.setNav();   

	}
}
