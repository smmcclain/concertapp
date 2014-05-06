package com.escapegoatdata.myke;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Cameras extends Activity {
	Tools t = new Tools();
	Integer[] pix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_screen);
        
        String title="Cameras";
        t.out(title);
        
        int nTracks = 3;
        String[] words = new String[] {"CAMERA 1", "CAMERA 2", "CAMERA 3"};
        pix = new Integer[] {R.drawable.camera1, R.drawable.camera2, R.drawable.camera3 }; 
        
        
        ArrayList<String[]> list = t.mkList(nTracks,words,pix);
        
		ListView lv = (ListView) findViewById(R.id.lv);
	
		final MyKeAdapter ela = new MyKeAdapter(this,  R.layout.paired_item, list, Data.PAIR);
		
		lv.setAdapter(ela);      
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getApplicationContext(), Camera.class);
				i.putExtra(Data.CAMNUM, String.valueOf(arg2 + 1));
				i.putExtra(Data.CAMERA, String.valueOf(pix[arg2]));
				startActivity(i);
			}
			
		});
        
	    Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
	    header.setHead(title);     
	    Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
	    footer.setNet();   
       
	}
	
}
