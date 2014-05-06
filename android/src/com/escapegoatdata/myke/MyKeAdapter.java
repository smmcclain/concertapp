package com.escapegoatdata.myke;

import java.util.ArrayList;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class MyKeAdapter extends ArrayAdapter<String[]>{

	Context ctx;
	ArrayList<String[]> myList;
	int type;
	int layout;
	View view;
	int row;
	Tools t = new Tools();
	String[] listItem;
	
	public MyKeAdapter(Context context, int inLay, ArrayList<String[]> objects, int inType) {
		super(context, inLay, objects);
		
		type = inType;
		ctx = context;
		myList = objects;
		layout = inLay;
		t.out("WELCOME TO MYKE ADAPTER");
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		view = convertView;
		row = position;
		int color;
		
		try {
			
			// FIRST TIME THROUGH POPULATE THE VIEW
			if (view == null) {
				t.out("FIRST VIEW");
				LayoutInflater vi = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = vi.inflate(layout,null); 
            } 
			
			listItem = (String[]) myList.get(position);
			
			TextView name = (TextView) view.findViewById(R.id.name);
			
        	switch (type) {
        	case Data.PAIR:
        	
                ImageView ppix = (ImageView) view.findViewById(R.id.pixi);
                ppix.setAdjustViewBounds(true);
           		ppix.setMaxHeight(Config.pairPixHeight);
           		ppix.setMaxWidth(Config.pairPixWidth);
          		ppix.setScaleType(ScaleType.FIT_XY);
          		ppix.setImageResource(Integer.parseInt(listItem[1]));		
        		ppix.setBaselineAlignBottom(true);
        		RelativeLayout.LayoutParams pParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                pParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
               	ppix.setLayoutParams(pParams);
               	ppix.setPadding(0,5,0,5);
                   	
                    	
                name.setText(listItem[0]);
               	name.setTextSize(Config.pairNameSize);
                name.setTextColor(ctx.getResources().getColor(Config.pairNameColor));
                name.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);             
                RelativeLayout.LayoutParams pnaParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                pnaParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                name.setLayoutParams(pnaParams);
                            
                color = Config.oddListColor;
                if (position % 2 == 0) {
                	color = Config.evenListColor;
                }
                
                view.setBackgroundColor(ctx.getResources().getColor(color));

        	break;
        	
        	case Data.SHOW:
        		
        		ImageView sLogo = (ImageView) view.findViewById(R.id.pix);
        		sLogo.setAdjustViewBounds(true);
        		sLogo.setMaxHeight(Config.showButtonHeight);
        		sLogo.setMaxWidth(Config.showButtonWidth);
        		sLogo.setScaleType(ScaleType.FIT_XY);
        		sLogo.setImageResource(Integer.parseInt(listItem[0]));
        		sLogo.setPadding(2,2,2,2);
        		
        		break;
        		
        	case Data.MYKE:
        		
        		ImageView mLogo = (ImageView) view.findViewById(R.id.mpix);
        		mLogo.setAdjustViewBounds(true);
//        		mLogo.setMaxHeight(Config.bigButtonHeight);
//        		mLogo.setMaxWidth(Config.bigButtonWidth);

        		mLogo.setMaxHeight(Config.mainButtonHeight);
        		mLogo.setMaxWidth(Config.mainButtonWidth);
        		
        		mLogo.setScaleType(ScaleType.FIT_XY);
        		mLogo.setImageResource(Integer.parseInt(listItem[0]));
        		mLogo.setPadding(2,2,2,2);
        		
        		break;
        		
        	case Data.TICKET:
        		
        		name.setText(listItem[0]);
    
        		TextView tPrice = (TextView) view.findViewById(R.id.seatprice);
        		tPrice.setText("$" + Config.ticketPrice);
        		
        		break;
        		
        	case Data.TOUR:
        		
        		name.setText(listItem[0]);
               	name.setTextSize(Config.tourInfoSize);
                name.setTextColor(ctx.getResources().getColor(Config.tourInfoColor));
                name.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
                name.setPadding(0, 10, 5, 5);
                
                color = Config.oddListColor;
                if (position % 2 == 0) {
                	color = Config.evenListColor;
                }
                
                view.setBackgroundColor(ctx.getResources().getColor(color));           
        		break;
        		
        	case Data.TRACKS:
        		name.setText(listItem[0]);
               	name.setTextSize(Config.tourInfoSize);
                name.setTextColor(ctx.getResources().getColor(Config.tourInfoColor));
                name.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
                name.setPadding(0, 10, 5, 5);
                
                ImageView tpix = (ImageView) view.findViewById(R.id.tpix);
                tpix.setAdjustViewBounds(true);
           		tpix.setMaxHeight(Config.pairPixHeight);
           		tpix.setMaxWidth(Config.pairPixWidth);
          		tpix.setScaleType(ScaleType.FIT_XY);
          		tpix.setImageResource(Integer.parseInt(listItem[1]));		
        		tpix.setBaselineAlignBottom(true);
        		RelativeLayout.LayoutParams tParams = new RelativeLayout.LayoutParams(Data.WRAP, Data.WRAP);
                tParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
               	tpix.setLayoutParams(tParams);
               	tpix.setPadding(0,5,0,5);
                
                
                color = Config.oddListColor;
                if (position % 2 == 0) {
                	color = Config.evenListColor;
                }
                
                view.setBackgroundColor(ctx.getResources().getColor(color));    
        		break;
        		
        	default:
        		break;
        	}
			
		} catch (Exception e) {
			t.out(e.toString());
		} 
		
		return view;
	}


	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}


	private View getCustomView(int position, View convertView, ViewGroup parent) {
				
		LayoutInflater inflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=inflater.inflate(layout, parent, false);
		ImageView icon = null;
		int height = Config.spinnerIconHeight;
		int width = Config.spinnerIconWidth;
		if (type == Data.MYKE) {
			icon=(ImageView)row.findViewById(R.id.mpix);
			height = Config.bigButtonHeight;
			width = Config.bigButtonWidth;
		} else {
			icon=(ImageView)row.findViewById(R.id.pix);
		}
		
		row.setBackgroundColor(ctx.getResources().getColor(R.color.white));
		
		String[] item = (String[]) myList.get(position);

		
		
		if (position > 0) { // skip the first one
			icon.setImageResource(Integer.parseInt(item[0]));
			icon.setAdjustViewBounds(true);
			icon.setMaxHeight(height);
			icon.setMaxWidth(width);
			icon.setScaleType(ScaleType.FIT_XY);
		}
		return row;
		
	}



	

}
