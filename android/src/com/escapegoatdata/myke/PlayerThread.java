package com.escapegoatdata.myke;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;

public class PlayerThread extends Thread {
	private SurfaceHolder surfaceHolder;
	Context ctx;
	MediaPlayer mp;
	String video;
	Tools t = new Tools();
	
	public PlayerThread(SurfaceHolder surfaceHolder, Context context, String inVid) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.ctx = context;
		this.video = inVid;
	}

	
	@Override
	public void run() {

		mp = new MediaPlayer();
		mp.setSurface(surfaceHolder.getSurface());
		 AssetFileDescriptor descriptor;
		 
		 if (mp.isPlaying()) {
			
			 t.out("WILL NOT RESTART");
			
		 } else {
		 
			try {
				descriptor = ctx.getAssets().openFd(video);
				mp.setDataSource( descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength() );
		        descriptor.close();
		        mp.prepare();
		        mp.start();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
	
	public void stopPlayer() {
		mp.stop();
		this.interrupt();
		try {
			this.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
