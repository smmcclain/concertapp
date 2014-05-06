package com.escapegoatdata.myke;

import java.lang.Thread.State;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class VideoPanel extends SurfaceView implements SurfaceHolder.Callback {
	private PlayerThread thread;
	Context ctx;
	Tools t = new Tools();
	
	// added for use by other tools
	public VideoPanel(Context context) {
		super(context);
	}

	public VideoPanel(Context context, String video) {
		super(context);
		
		ctx = context;
		
		// adding the callback (this) to the surface holder to intercept events
	     getHolder().addCallback(this);
	        
	     // create the game loop thread
	    thread = new PlayerThread(getHolder(), context, video);
	        
	    // make the GamePanel focusable so it can handle events
	        setFocusable(true);
		
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
        boolean retry = true;
	       while (retry) {
	            try {
	            	thread.join();
	                retry = false;
	            } catch (InterruptedException e) {
	                // try again shutting down the thread
            }
	     }
	}

	
	public void startPlayer() {
		 State state = thread.getState();
		 
		 t.out(" STATE = " + state.toString());
		 
		 if (thread.isAlive()) {
			 t.out("ALREADY RUNNING");
		 } else {
			 try {
				 thread.start(); 
			 } catch (Exception e) {
				 e.printStackTrace();
				 thread.stopPlayer();
		//		 thread.start();
			 }
		 }
	}
	
	public void stopPlayer() {
		 thread.stopPlayer();
		 
	}
	
	public String getState() {
		return  thread.getState().toString();
	}
}
