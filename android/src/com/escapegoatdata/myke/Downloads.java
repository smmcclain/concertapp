package com.escapegoatdata.myke;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Downloads extends Activity {
	Tools t = new Tools();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_screen);

        String title ="Downloads"; 
        t.out(title);
        
        int nArt = 3; 
        String[] words = new String[nArt]; 
        Integer[] pix = new Integer[nArt];
        
        words[0] = "Get Music";
        words[1] = "Get Lyrics";
        words[2] = "Get Video";
        
        pix[0] = R.drawable.song_button;
        pix[1] = R.drawable.lyrics;
        pix[2] = R.drawable.video;
        
        
        ArrayList<String[]> list = t.mkList(nArt,words,pix);
        
		ListView lv = (ListView) findViewById(R.id.lv);

		final MyKeAdapter ela = new MyKeAdapter(this,  R.layout.paired_item, list, Data.PAIR);
		
		lv.setAdapter(ela);
        
        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title);     
        Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
        footer.setNet();
   
	}
	
}
