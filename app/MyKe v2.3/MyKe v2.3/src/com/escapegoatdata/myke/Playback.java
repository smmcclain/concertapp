package com.escapegoatdata.myke;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class Playback extends Activity {
	Tools t = new Tools();
	MediaPlayer player;
	String type;
	VideoPanel vp;
	String file;
	boolean videoOn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blank_view_screen);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.content);
    
        String title="Playback";
        videoOn = false;
        
		RelativeLayout relay = new RelativeLayout(this);
        RelativeLayout.LayoutParams llParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,Config.playbackHeight);
        llParams.addRule( RelativeLayout.BELOW, R.id.header );
        relay.setId(Data.plabaId);
        relay.setLayoutParams(llParams);
        
        
        Intent i = getIntent();
        type = t.getEx(i, Data.AV);
        
    	file = "video/Woodstock_Festival_Trailer_512kb.mp4";
    	int image = R.drawable.videocam;

        AssetFileDescriptor afd = null;
                
        if (type.equals(Data.AUDIO)) {

            file = "music/Days.mp3";
            image = R.drawable.song_button;
            
            ImageView iv = new  ImageView(getApplicationContext());
            iv.setAdjustViewBounds(true);
            iv.setMaxHeight(Config.mainButtonHeight);
            iv.setImageResource(image);
            RelativeLayout.LayoutParams ivp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            ivp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            iv.setLayoutParams(ivp);
            iv.setId(Data.plabaId);
           
            relay.addView(iv);
   
       
            player = new MediaPlayer();
        
  
            try {
        
            	t.out("FILE = " + file);
            	afd = getAssets().openFd(file);
            	player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
       	 		afd.close();
       	 		player.prepare();
	
            } catch (IOException e) {
            	e.printStackTrace();
            } catch (IllegalArgumentException e) {
    			e.printStackTrace();
    		} catch (IllegalStateException e) {
    			e.printStackTrace();
    		} 
        } else {       
        
    		vp = new VideoPanel(this, file);
    		relay.addView(vp);
            
		}

        // ADD THE START STOP BUTTONS
        LinearLayout stst = t.mkLinearLayout(getApplicationContext(), LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams stp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        stp.addRule(RelativeLayout.BELOW,Data.plabaId);
        stst.setLayoutParams(stp);
        
        ImageButton start = t.mkImageButton(getApplicationContext(), R.drawable.start, Config.ststButtonWidth, Config.ststButtonHeight);
        ImageButton stop = t.mkImageButton(getApplicationContext(), R.drawable.stop, Config.ststButtonWidth, Config.ststButtonHeight);
        
        start.setBackgroundColor(getResources().getColor(R.color.white));
        stop.setBackgroundColor(getResources().getColor(R.color.white));
        
        start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				if (videoOn) {
					t.out("I am already turned on.");
				} else {
				
					if (type.equals(Data.AUDIO)) {
						player.start();	
					} else {
					
						try {
							vp.stopPlayer();
						} catch(Exception e) {
							t.out("NOT STARTED YET");
						}
					
						if (vp.getState().equals("TERMINATED")) {
							vp = new VideoPanel(getApplicationContext(),file);	
						}
				
						if (vp.getState().equals("NEW")) {
							vp.startPlayer();
							videoOn = true;
						}
					}
				}
				
			}
        }); 
        
        stop.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (type.equals(Data.AUDIO)) {
					player.stop();
					player.release();
				} else {
					if (vp.getState().equals("NEW")) {
						t.out("You cannot stop what you did not start");
					} else {
						t.out("STATE = " + vp.getState());
					
						try {
							vp.stopPlayer();
							videoOn = false;
						} catch (Exception e) {
							t.out("NOW WHAT?");
						}				
					}
				}
				// stop and reload
				Intent i = new Intent(getApplicationContext(),Playback.class);
				i.putExtra(Data.AV, type);
				startActivity(i);
			} 
        }); 
        
        stst.addView(start);
        stst.addView(stop);
        rl.addView(relay);
        rl.addView(stst);
        
  
        
        Header header = (Header) getFragmentManager().findFragmentById(R.id.header);
        header.setHead(title); 
        Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
        footer.setNav();
	}
	
	
	
}
