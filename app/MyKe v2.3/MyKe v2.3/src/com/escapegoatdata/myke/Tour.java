package com.escapegoatdata.myke;

import java.sql.Date;
import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Tour extends Fragment {
	
	Tools t = new Tools();
	Concert[] c;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);	
		
		return inflater.inflate(R.layout.tour_frag_screen, container);
	}
	
	public void tourDates(int bid) {
		
		Concert concert = new Concert(0,0,"" , new Date(0));
		c = concert.mkTour(bid);
		    
		int nDates = c.length;
		    
		String[] words = new String[nDates + 1]; // add an extra one so the last entry isn't covered by the footer
		Integer[] pix = new Integer[nDates + 1];
		
		String msg;
		Venue v;
		for (int j = 0; j<nDates; j++) {
			v = Venue.getVenue(c[j].vid);
			msg = c[j].date.toString() +  " | " + v.city;
			words[j] = msg;
			pix[j] = v.logo;
			t.out(msg);
		}
		
		// NOTHING AT THE BOTTOM
		words[nDates] = "";
		pix[nDates] = R.drawable.blank;
		    
		ArrayList<String[]> list = t.mkList(nDates + 1,words,pix);
		    
		ListView lv = (ListView) getView().findViewById(R.id.lv);
			
		final MyKeAdapter ela = new MyKeAdapter(getActivity().getApplicationContext(),  R.layout.paired_item, list, Data.TOUR);
			
		lv.setAdapter(ela);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getActivity().getApplicationContext(),VenuePage.class);
				i.putExtra(Data.VID, String.valueOf(c[arg2].vid));
				startActivity(i);
					}
				
			});	
		    
		
	}
	
	public void getSetlist(int bid) {
		
		String[] words = new String[Data.nSongs + 2]; // add an extra one so the last entry isn't covered by the footer
		Integer[] pix = new Integer[Data.nSongs + 2];
		
		words[0] = "Louie, Louie";
		words[1] = "Who's Your Daddy?";
		words[2] = "YMCA";
		words[3] = "Big Truck, Little...";
		words[4] = "1999";
		words[5] = "Rocky Raccoon";
		words[6] = "----";
		words[7] = "----";

		for (int j = 0; j < Data.nSongs + 2; j++) {
			pix[j] = R.drawable.blank;
		}
		

		    
		ArrayList<String[]> list = t.mkList(Data.nSongs + 2,words,pix);
		    
		ListView lv = (ListView) getView().findViewById(R.id.lv);
			
		final MyKeAdapter ela = new MyKeAdapter(getActivity().getApplicationContext(),  R.layout.paired_item, list, Data.TOUR);
			
		lv.setAdapter(ela);
		
	}

}
