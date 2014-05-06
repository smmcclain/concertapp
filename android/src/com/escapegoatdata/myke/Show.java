package com.escapegoatdata.myke;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class Show extends Activity {
	Tools t = new Tools();
	int eid;
	Event e;
	int nActs;
	ImageButton[] ib;
	Spinner audio;
	Spinner video;
	Spinner fame;
	ScrollView sv;
	Spinner myke;
	int onStage = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blank_view_screen);
        RelativeLayout relay = (RelativeLayout) findViewById(R.id.content);
        
  
        String eidstr = t.getEx(getIntent(), Data.EID);
        
        if (eidstr.equals(Data.FILL)) {
        	Data.random.nextInt(Data.nEvents );
        } else {
        	eid = Integer.parseInt(eidstr);
        }
        
        e = Event.getEvent(eid);
        String title = e.name;
          
        
     // TOP HORIZONTAL SCROLL OF ACTS 
        
        HorizontalScrollView hsv = new HorizontalScrollView(this);
        RelativeLayout.LayoutParams hsvParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        hsvParams.addRule(RelativeLayout.BELOW,R.id.header);
        hsv.setLayoutParams(hsvParams);
        hsv.setId(Data.horScroId);
        
        LinearLayout acts = new LinearLayout(this);
        RelativeLayout.LayoutParams lscParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        acts.setLayoutParams(lscParams);
        acts.setOrientation(LinearLayout.HORIZONTAL);
        acts.setId(Data.hsvId);
        
        
		nActs = e.nActs;

		ib = new ImageButton[nActs];
		LinearLayout[] bInfo = new LinearLayout[nActs];

	    for (int iBand = 0; iBand < nActs; iBand++) {
	    	bInfo[iBand] = new LinearLayout(this);
	    	bInfo[iBand].setOrientation(LinearLayout.VERTICAL); 
	    	bInfo[iBand].setLayoutParams(new LayoutParams(Config.horizScrollBandPixWidth,LayoutParams.WRAP_CONTENT));
	    	ib[iBand] = new ImageButton(this);
	    
	    	Band b = Band.getBand(e.lineup[iBand]);

	    	Drawable replacer = getResources().getDrawable(b.pix);
	    	ib[iBand].setImageDrawable(replacer);
	    	ib[iBand].setContentDescription(b.name);
	    	ib[iBand].setPadding(5, 5, 5, 5);
	    	ib[iBand].setScaleType(ScaleType.FIT_CENTER);
	    	ib[iBand].setOnClickListener(bcl);
	    	ib[iBand].setAdjustViewBounds(true);
	    	ib[iBand].setMaxHeight(Config.horizScrollHeight);
	    	ib[iBand].setId(Data.horScroId);
	    	ib[iBand].setBackgroundColor(getResources().getColor(R.color.white));
	    	
	    	String timecap = "Show recap";
	    	if (iBand == onStage) {
	    		timecap = "Now playing";
	    	}
	    	if (iBand > onStage) {
	    		timecap = e.showtime[iBand];
	    	}
	    	
	    	bInfo[iBand].addView(ib[iBand]);
	    	
	    	TextView cap = new TextView(this);
	    	RelativeLayout.LayoutParams caParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    	caParams.addRule(RelativeLayout.BELOW, Data.horScroId);
	    	cap.setLayoutParams(caParams);
	    	cap.setText(timecap);
	    	cap.setTypeface(null,Typeface.BOLD);
	    	cap.setPadding(0, 10, 0, 0);
	    	
	    	bInfo[iBand].addView(cap);
	    		    		    	
	    	acts.addView(bInfo[iBand]);
	    }

        
        hsv.addView(acts);
        relay.addView(hsv);      
        
        RelativeLayout innerlay = new RelativeLayout(this);
        RelativeLayout.LayoutParams svParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,Data.MATCH);
        svParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        innerlay.setLayoutParams(svParams);
  
        
   //     innerlay.setPadding(0, 20, 0, 0);
        
        RelativeLayout row0 = t.mkRelativeLayout(this,RelativeLayout.CENTER_HORIZONTAL);
        row0.setId(Data.r0Id);
        row0.setPadding(0, 185, 0, 0);
        
        // Load the ImageView that will host the animation and
        // set its background to our AnimationDrawable XML resource.
        ImageView gaga =  new ImageView(this);
        RelativeLayout.LayoutParams gagalp = t.mkLayPar(Config.vidWidth, Config.vidHeight);
        gagalp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        gagalp.addRule(RelativeLayout.BELOW,Data.horScroId);
        gaga.setLayoutParams(gagalp);
       
        
        gaga.setBackgroundResource(R.drawable.gaga);

        // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) gaga.getBackground();

        // Start the animation (looped playback by default).
        frameAnimation.start();

       
        row0.addView(gaga); 
       

        RelativeLayout row1 = t.mkRelativeLayout(this, RelativeLayout.CENTER_HORIZONTAL);
        RelativeLayout.LayoutParams r1lp = t.mkLayPar(Data.MATCH, Data.MATCH);
    //    r1lp.addRule(RelativeLayout.BELOW,Data.r0Id);
        r1lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        row1.setLayoutParams(r1lp);
        
        ArrayList<String[]> audioList = new ArrayList<String[]>(6);
        ArrayList<String[]> videoList = new ArrayList<String[]>(6);
        ArrayList<String[]> mykeList = new ArrayList<String[]>(2);
    
        audioList.add(new String[] {String.valueOf(R.drawable.audio)});
        audioList.add(new String[] {String.valueOf(R.drawable.record)});
        audioList.add(new String[] {String.valueOf(R.drawable.playback)});
        audioList.add(new String[] {String.valueOf(R.drawable.tracks)});
        audioList.add(new String[] {String.valueOf(R.drawable.whisper)});
        audioList.add(new String[] {String.valueOf(R.drawable.share)}); 
        
        videoList.add(new String[] {String.valueOf(R.drawable.video)});
        videoList.add(new String[] {String.valueOf(R.drawable.record)});
        videoList.add(new String[] {String.valueOf(R.drawable.playback)});
        videoList.add(new String[] {String.valueOf(R.drawable.cameras)});
        videoList.add(new String[] {String.valueOf(R.drawable.backstage_icon)});
        videoList.add(new String[] {String.valueOf(R.drawable.share)}); 
         
        mykeList.add(new String[] {String.valueOf(R.drawable.myke_logo)});
        mykeList.add(new String[] {String.valueOf(R.drawable.myke_yourself)});
        
        MyKeAdapter maud = new MyKeAdapter(this,R.layout.show_spinner_item,audioList, Data.SHOW);
        MyKeAdapter mvid = new MyKeAdapter(this,R.layout.show_spinner_item,videoList, Data.SHOW);
        MyKeAdapter mykead = new MyKeAdapter(this,R.layout.myke_spinner_item,mykeList, Data.MYKE);
        
        audio = new Spinner(this);
        maud.setDropDownViewResource(R.layout.show_spinner_item);
        audio.setAdapter(maud);
		audio.setOnItemSelectedListener(audioChoiceListener);
		
        video = new Spinner(this);
        mvid.setDropDownViewResource(R.layout.show_spinner_item);
        video.setAdapter(mvid);
		video.setOnItemSelectedListener(videoChoiceListener);
		
		myke = new Spinner(this);
		mykead.setDropDownViewResource(R.layout.myke_spinner_item);
		myke.setAdapter(mykead);
		myke.setOnItemSelectedListener(mykeChoiceListener);
        
		
/*
        ImageButton myke = t.mkImageButton(this, R.drawable.myke_logo, Config.mainButtonWidth, Config.mainButtonHeight);
        myke.setOnClickListener(ocl);
        myke.setTag(Data.MYKE);
        myke.setBackgroundColor(getResources().getColor(R.color.white));
        myke.bringToFront();
  */      
        
        RelativeLayout.LayoutParams rla = t.mkLayPar(Data.WRAP, Data.WRAP);
        rla.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rla.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        audio.setLayoutParams(rla);
        
        RelativeLayout.LayoutParams rlm = t.mkLayPar(Data.WRAP, Data.WRAP);
        rlm.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rlm.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        myke.setLayoutParams(rlm);
        
        RelativeLayout.LayoutParams rlv = t.mkLayPar(Data.WRAP, Data.WRAP);
        rlv.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        video.setLayoutParams(rlv);
        
        
        row1.addView(audio);
        row1.addView(myke);
        row1.addView(video);

        innerlay.addView(row0);
        innerlay.addView(row1);
        relay.addView(innerlay);   
        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title);  
        
	}
	
	
	
	OnItemSelectedListener audioChoiceListener = new OnItemSelectedListener() {


		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			t.out("AUDIO CHOICE " + arg2);
			
			Intent i = new Intent(getApplicationContext(), Show.class);
			i.putExtra(Data.EID, String.valueOf(eid));
			arg0.setSelection(0);
			
			switch(arg2) {
			case 0:
				break;
			case 1:
				//RECORD
				startActivity(new Intent(getApplicationContext(),RecordAudio.class));
				break;
			case 2:
				//PLAYBACK
				i = new Intent(getApplicationContext(),Playback.class);
				i.putExtra(Data.AV,Data.AUDIO);
				startActivity(i);
				break;
			case 3:
				startActivity(new Intent(getApplicationContext(),Tracks.class));
				break;
			case 4:
				//SHARE
				startActivity(new Intent(getApplicationContext(),Whisper.class));
				break;
			case 5:
				//SHARE
				startActivity(new Intent(getApplicationContext(),Share.class));
				break;
			default:
				// Reload page
				startActivity(i);
				break;
					
			}
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			arg0.setSelection(0);
			
		}		
	};
	
	OnItemSelectedListener mykeChoiceListener = new OnItemSelectedListener() {


		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			t.out("MYKE CHOICE " + arg2);
			arg0.setSelection(0);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			arg0.setSelection(0);
			
		}		
	};
	
	
	
	OnItemSelectedListener videoChoiceListener = new OnItemSelectedListener() {


		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			t.out("VIDEO CHOICE " + arg2);
			
			Intent i = new Intent(getApplicationContext(), Show.class);
			i.putExtra(Data.EID, String.valueOf(eid));
			arg0.setSelection(0);
			
			switch(arg2) {
			case 0:
				break;
			case 1:
				//RECORD
				startActivity(new Intent(getApplicationContext(),RecordVideo.class));
				break;
			case 2:
				//PLAYBACK
				i = new Intent(getApplicationContext(),Playback.class);
				i.putExtra(Data.AV,Data.VIDEO);
				startActivity(i);
				break;
			case 3:
								//CAMERAS
				startActivity(new Intent(getApplicationContext(),Cameras.class));
				break;
			case 4:
				//SHARE
				startActivity(new Intent(getApplicationContext(),Backstage.class));
				break;
			case 5:
				//SHARE
				startActivity(new Intent(getApplicationContext(),Share.class));
				break;
			default:
				// Reload page
				startActivity(i);
				break;
					
			}
			
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {

			arg0.setSelection(0);
		}		
	};

	OnClickListener bcl = new OnClickListener()  {
		
		@Override
		public void onClick(View view) {
			t.out( "BAND BUTTON PUSHED"); 
			Intent who = null;
					
			for(int i=0; i<nActs;i++) {
				if (view == ib[i]) {
					if (i < onStage ) {
						who = new Intent(getApplicationContext(), Recap.class);
					} else {
						who = new Intent(getApplicationContext(), BandPage.class);
					}
					t.out("i = " + i);
					String bid = String.valueOf(Data.events[e.eid].lineup[i]);		
					who.putExtra(Data.BID, bid); 
					startActivity(who);
				}
			}
		}
	};
	
    OnClickListener ocl = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent i = Listeners.getClickIntent(getApplicationContext(), (Integer) arg0.getTag());
			startActivity(i);
		}
    };	
	
}
