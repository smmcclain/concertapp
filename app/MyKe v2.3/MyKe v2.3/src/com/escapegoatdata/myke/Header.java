package com.escapegoatdata.myke;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class Header extends Fragment {
	Tools t = new Tools();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);	
		
		View v = inflater.inflate(R.layout.header_screen, container);
		v.bringToFront();
		return v;
	}


	public void setHead(String title) {
		TextView banner = (TextView) getView().findViewById(R.id.title);
			
		
		banner.setTextColor(getResources().getColor(Config.bannerTextColor));
		banner.setTextSize(Config.bannerTextSize);
		banner.setBackgroundColor(getResources().getColor(Config.bannerBackgroundColor));
		
		ImageView iv = (ImageView) getView().findViewById(R.id.pix);
		iv.setImageResource(R.drawable.home_botbut);
		iv.setAdjustViewBounds(true);
		iv.setMaxHeight(Config.homeButtonHeight);
		iv.setMaxWidth(Config.homeButtonWidth);
		iv.setScaleType(ScaleType.FIT_XY);
		iv.setOnClickListener(home);
		iv.bringToFront();
		
		t.out("HEADER");
	
		banner.setText(title);
		
	}
	
	public void setHeadSearchBar(String title) {
		TextView banner = (TextView) getView().findViewById(R.id.title);
			
		
		banner.setTextColor(getResources().getColor(Config.bannerTextColor));
		banner.setTextSize(Config.bannerTextSize);
		banner.setBackgroundColor(getResources().getColor(Config.bannerBackgroundColor));
		
		ImageView iv = (ImageView) getView().findViewById(R.id.pix);
		iv.setImageResource(R.drawable.searchbar);
		iv.setAdjustViewBounds(true);
		iv.setMaxHeight(Config.searchBarHeight);
		iv.setMaxWidth(Config.searchBarWidth);
		iv.setScaleType(ScaleType.FIT_XY);
		iv.setOnClickListener(home);
		iv.bringToFront();
		
		t.out("HEADER");
	
		banner.setText(title);
		
	}
	

	OnClickListener home  = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			startActivity(new Intent(getActivity().getApplicationContext(),MainActivity.class));	
		}
	};




	

	
}
