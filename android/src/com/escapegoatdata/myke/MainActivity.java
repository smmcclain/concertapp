package com.escapegoatdata.myke;

import com.escapegoatdata.myke.Listeners.URLGrabberHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class MainActivity extends Activity {

	Tools t = new Tools();

	@Override
	protected void onResume() {
		super.onResume();
		Listeners.setContext(this);
		URLGrabber.setMainHandler(new URLGrabberHandler());
        new Thread(new URLGrabber()).start();
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_screen);
        
        t.out("And it begins...");
        
        t.init();
            
        ScrollView sv = (ScrollView) findViewById(R.id.sv);
        LinearLayout linlay = t.mkLinearLayout(getApplicationContext(), LinearLayout.VERTICAL);

        RelativeLayout row1 = t.mkRelativeLayout(this, RelativeLayout.CENTER_HORIZONTAL);
        RelativeLayout row2 = t.mkRelativeLayout(this, RelativeLayout.CENTER_HORIZONTAL);
        row2.setPadding(0, 20, 0, 340);
        
        ImageButton showTime = t.mkImageButton(this, R.drawable.show_button, Config.bigButtonWidth, Config.bigButtonHeight);
        RelativeLayout.LayoutParams stParams = t.mkLayPar(Data.WRAP,Data.WRAP);        
        stParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        showTime.setLayoutParams(stParams);
        showTime.setOnClickListener(ocl);
        showTime.setTag(Data.SHOW);
        showTime.setBackgroundColor(getResources().getColor(R.color.white));
        
        ImageButton search = t.mkImageButton(this, R.drawable.search, Config.mainButtonWidth, Config.mainButtonHeight);
        RelativeLayout.LayoutParams seParams = t.mkLayPar(Data.WRAP,Data.WRAP);
        seParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        search.setLayoutParams(seParams);
        search.setOnClickListener(ocl);
        search.setTag(Data.SEARCH);
        search.setBackgroundColor(getResources().getColor(R.color.white));
             
        
        ImageButton stuff = t.mkImageButton(this, R.drawable.stuff, Config.mainButtonWidth, Config.mainButtonHeight);
        RelativeLayout.LayoutParams sfParams = t.mkLayPar(Data.WRAP,Data.WRAP);
        sfParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        stuff.setLayoutParams(sfParams);
        stuff.setOnClickListener(ocl);
        stuff.setTag(Data.STUFF);
        stuff.setBackgroundColor(getResources().getColor(R.color.white));
             
        row1.addView(showTime);
        row2.addView(search);
        row2.addView(stuff);
        linlay.addView(row1);
        linlay.addView(row2);
        sv.addView(linlay);
        
        Network footer = (Network) getFragmentManager().findFragmentById(R.id.network);
        footer.setNet();
      
    }
    
    
    OnClickListener ocl = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent i = Listeners.getClickIntent(getApplicationContext(), (Integer) arg0.getTag());
			startActivity(i);
		}
    };	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
