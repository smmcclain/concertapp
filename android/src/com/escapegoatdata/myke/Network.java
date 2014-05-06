package com.escapegoatdata.myke;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Network extends Fragment {
	Tools t = new Tools();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);	
		
		return inflater.inflate(R.layout.footer_screen, container);
	}

	public void setNav() {
		Context ctx = getActivity().getApplicationContext();
		
		Tools t = new Tools();
		
		RelativeLayout relay = (RelativeLayout) getView().findViewById(R.id.nav);
		
        ImageButton search = t.mkImageButton(ctx, R.drawable.search_botbut, Config.botButtonWidth, Config.botButtonHeight);
        RelativeLayout.LayoutParams stParams = t.mkLayPar(Data.WRAP,Data.WRAP);        
        stParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        search.setLayoutParams(stParams);
        search.setOnClickListener(ocl);
        search.setTag(Data.SEARCH);
        search.setBackgroundColor(getResources().getColor(R.color.red));

        
        ImageButton tix = t.mkImageButton(ctx, R.drawable.tickets_botbut, Config.botButtonWidth, Config.botButtonHeight);
        RelativeLayout.LayoutParams seParams = t.mkLayPar(Data.WRAP,Data.WRAP);
        seParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        tix.setLayoutParams(seParams);
        tix.setOnClickListener(ocl);
        tix.setTag(Data.TICKET);
        tix.setBackgroundColor(getResources().getColor(R.color.red));
             
        
        ImageButton home = t.mkImageButton(ctx, R.drawable.home_botbut, Config.botButtonWidth, Config.botButtonHeight);
        RelativeLayout.LayoutParams sfParams = t.mkLayPar(Data.WRAP,Data.WRAP);
        sfParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        home.setLayoutParams(sfParams);
        home.setOnClickListener(ocl);
        home.setTag(Data.HOME);
        home.setBackgroundColor(getResources().getColor(R.color.red));
             
        relay.addView(search);
        relay.addView(tix);
        relay.addView(home);
        relay.setBackgroundColor(ctx.getResources().getColor(R.color.red));		
	}
	
	
	public void setTix() {
		
		Context ctx = getActivity().getApplicationContext();
		
		Tools t = new Tools();
		
		RelativeLayout relay = (RelativeLayout) getView().findViewById(R.id.nav);
		
		
		TextView tv = new TextView(ctx);
		tv.setText("Get Tickets");
		tv.setTextColor(getResources().getColor(R.color.white));
		RelativeLayout.LayoutParams tvp = t.mkLayPar(Data.WRAP, Data.WRAP);
		tvp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		tv.setLayoutParams(tvp);
		tv.setTextSize(Config.bannerTextSize);
		
		relay.setBackgroundColor(getResources().getColor(R.color.red));
		relay.setTag(Data.TICKET);
		relay.setOnClickListener(ocl);
		
		relay.addView(tv);
		
		relay.bringToFront();
		
	}
	
	
	
	OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			
			t.out("CHOICE " + arg0.toString());
			Intent i = Listeners.getClickIntent(getActivity().getApplicationContext(), (Integer) arg0.getTag());
			startActivity(i);
			
		}
	};

	
	
	public void setNet() {
		TextView teaser = (TextView) getView().findViewById(R.id.news);
			
		
		teaser.setTextColor(getResources().getColor(Config.teaserColor));
		teaser.setTextSize(Config.teaserSize);
		teaser.setPadding(Config.networkButtonWidth + 10,0,0,0);
		
		RelativeLayout.LayoutParams teaParams = new RelativeLayout.LayoutParams(Data.MATCH,Config.networkButtonHeight - 10);
		teaParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		teaser.setLayoutParams(teaParams);
		teaser.setBackgroundColor(getResources().getColor(Config.teaserBackgroundColor));
		
		ImageView iv = (ImageView) getView().findViewById(R.id.pix);
		iv.setImageResource(R.drawable.network_button);
		iv.setAdjustViewBounds(true);
		iv.setMaxHeight(Config.networkButtonHeight);
		iv.setMaxWidth(Config.networkButtonWidth);
		iv.setScaleType(ScaleType.FIT_XY);
		
		t.out("NETWORKING");

//		String teaseMe = getTeaserText();	
//		teaser.setText(teaseMe);
		
		
		ImageView news = (ImageView) getView().findViewById(R.id.newspix);
	    news.setBackgroundResource(R.drawable.news);

	        // Get the background, which has been compiled to an AnimationDrawable object.
	    AnimationDrawable frameAnimation = (AnimationDrawable) news.getBackground();

	        // Start the animation (looped playback by default).
	        frameAnimation.start();
		
		
		
	}
	
	String getTeaserText() {
		int rt = Data.random.nextInt(Data.nTeasers);
		return Data.teasers[rt];
	}
	
/*

*/




	

	
}
