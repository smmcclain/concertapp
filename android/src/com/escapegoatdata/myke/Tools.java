package com.escapegoatdata.myke;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Tools {
	
	public ArrayList<String[]> mkList(int n, String[] words, Integer[] pix) {
		ArrayList<String[]> list = new ArrayList<String[]>(n);
	
		for( int i = 0; i < n; i++) {
			list.add(new String[] { words[i], String.valueOf(pix[i])});	
		}
	
		return list;
	}


	

	
	




	
	public ImageButton mkHomeButton(Context ctx) {
		
	    // HOME BUTTON
		
        ImageButton ib = mkImageButton(ctx,R.drawable.home_botbut,Config.homeButtonWidth, Config.homeButtonHeight);
		ib.setBaselineAlignBottom(true);
		RelativeLayout.LayoutParams ibParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		ibParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		ib.setLayoutParams(ibParams);
		ib.setBackgroundColor(ctx.getResources().getColor(R.color.red));
		
	
		return ib;
	}
	

	public RelativeLayout mkSimpleImage(Context inContext, int image) {
		
		RelativeLayout irl = new RelativeLayout(inContext);
		RelativeLayout.LayoutParams irParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		irParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		irl.setLayoutParams(irParams);
		
		ImageView iv = new ImageView(inContext); 
		iv.setAdjustViewBounds(true);
		iv.setMaxHeight(Config.simpleImageHeight);
		iv.setScaleType(ScaleType.FIT_XY);
		iv.setImageResource(image);
		iv.setPadding(0, Config.simpleImageOffset , 0, 0);
		irl.addView(iv);
		
		return irl;
	}
	
	

	// REMOVE LATER
public RelativeLayout footer(Context ctx) {
	RelativeLayout ft = new RelativeLayout(ctx);
	return ft;
}

public ImageButton mkFootButton(Context ctx, RelativeLayout footer) {
	
	ImageButton network = mkImageButton(ctx,R.drawable.network_button,Config.networkButtonWidth, Config.networkButtonHeight);
	network.setPadding(5, 0, 0, 5);
	RelativeLayout.LayoutParams nwParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
	nwParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	network.setLayoutParams(nwParams);
	network.setId(Data.nwbId);
	
	return network;
}

//REMOVE LATER
	
// CHECKED OUT
	
	// PULL OUT AN EXTRA FROM AN INTENT
	public String getEx(Intent i, String varname) {
		
		String varval = Data.FILL;
		if (i.getStringExtra(varname) != null ) {
			varval= i.getStringExtra(varname);
			out("IN getEx " + varname + " = " + varval);
		} else {
			// NO VAR - Start Again
			out("booboo " + varname);
		}
		
		return varval;
	}
	


	

	
	public void out(String msg) {
		System.out.println(msg);
	}
	
	public LinearLayout mkLinearLayout(Context ctx, int rule) {
		LinearLayout linlay = new LinearLayout(ctx);
        linlay.setOrientation(rule);
        RelativeLayout.LayoutParams llParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        llParams.addRule( rule );
        linlay.setLayoutParams(llParams);
        return linlay;
	}
	
	public RelativeLayout mkRelativeLayout(Context ctx, int rule) {
		RelativeLayout relay = new RelativeLayout(ctx);
        RelativeLayout.LayoutParams llParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        llParams.addRule( rule );
        relay.setLayoutParams(llParams);
        return relay;
	}
	
   public RelativeLayout.LayoutParams mkLayPar(int width, int height) {
	   RelativeLayout.LayoutParams laypar = new RelativeLayout.LayoutParams(width,height);
	   return laypar;
   }
   
	public ImageButton mkImageButton(Context ctx, int image, int width, int height) {
		ImageButton ib = new ImageButton(ctx);
		ib.setImageResource(image);
//		ib.setBackgroundResource(image);
		ib.setAdjustViewBounds(true);
        ib.setMaxWidth(width);
        ib.setMaxHeight(height);
		ib.setScaleType(ScaleType.FIT_XY);
//        ib.setScaleType(ScaleType.CENTER_CROP);
		return ib;
	}
	
	public void init() {
		
		Teasers.load();
		Event.load();
		Band.load();
		Venue.load();
	
	}
	
	public RelativeLayout header(Context ctx, String title) {

		RelativeLayout header = new RelativeLayout(ctx);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		header.setLayoutParams(params);
		
		// BANNER TEXT
		TextView banner = new TextView(ctx);
		banner.setTextColor(ctx.getResources().getColor(Config.bannerTextColor));			
		banner.setBackgroundColor(ctx.getResources().getColor(Config.bannerBackgroundColor));
		banner.setText(title);
		banner.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
		banner.setTextSize(Config.bannerTextSize);
        banner.setGravity(Gravity.LEFT);    
        banner.setWidth(1000);
        header.addView(banner);
        header.setId(Data.headerId);
        
		return header;
	}
	
	public ImageView mkImageView(Context ctx, int image, int width, int height) {
		ImageView ib = new ImageView(ctx);
		ib.setImageResource(image);
		ib.setAdjustViewBounds(true);
        ib.setMaxWidth(width);
        ib.setMaxHeight(height);
		ib.setScaleType(ScaleType.FIT_XY);
		return ib;
	}
	
}
