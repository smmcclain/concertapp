package com.example.hello_tester;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{    
    /** main message handler */
    private URLGrabberHandler mainHandler;
    
    /** Listener transferring control to a retail detail view */
    private ShowRealtorListener onclick;
    
    /** Stores a list of all the Realtors returned from the Server */
    private List<Realtor> realtors = null;
    
    /** ListView's array adapter */
    private RealtorArrayAdapter adapter = null;
    
    /** Detail view */
    Dialog modal = null;

    /**
     * JSON object representing a Realtor
     */
    private class Realtor
    {
        private String name;
        private int id;
        private String rebrand;
        private String office;
        private String phone_number;
        private String photo;
        public Bitmap thumbnail = null;
        public Bitmap portrait = null;

        public Realtor(JSONObject obj)
        {
            try
            {
                name = obj.getString("name");
                id = obj.getInt("id");
                rebrand = obj.getString("rebrand");
                office = obj.getString("office");
                phone_number = obj.getString("phone_number");
                photo = obj.getString("photo");
            }
            catch (Exception e)
            {
            }
        }
    }

    /**
     * Background thread for retrieving data from network
     */
    private class URLGrabber implements Runnable
    {
        /** location of the content server */
        private static final String CONTENT_URL =
                "http://www.ebby.com/realtor_list?format=ios";

        /**
         * Retrieve raw data from a URL
         * 
         * @param url incoming URL containing the document to retrieve
         * @return the entire serialized document at the given URL
         */
        String getDataFromURL(String url)
        {
            HttpEntity entity;
            StringBuilder data = new StringBuilder();
            BufferedReader io;
            String line;
            
            try
            {
                if ( (entity = new DefaultHttpClient()
                             .execute(new HttpGet(url))
                             .getEntity()
                        ) == null)
                {
                    return null;
                }
                io = new BufferedReader ( new InputStreamReader (
                        entity.getContent()
                        ));
                while ((line = io.readLine()) != null)
                {
                    data.append(String.format("%s%n", line));
                }
            }
            catch (Exception e)
            {
                return null;
            }
            
            return data.toString();
        }

        /**
         * Attempt to grab data from the server and relay it to the main thread
         */
        @Override
        public void run()
        {
            Message msg = mainHandler.obtainMessage();
            String result = getDataFromURL(CONTENT_URL);
            
            if (result != null)
            {
                msg.obj = result;
                mainHandler.sendMessage(msg);
            }
        }
    }

    /**
     * Holds information for a collection of Realtors; used to back a ListView
     */
    private class RealtorArrayAdapter extends ArrayAdapter<Realtor>
    {
        /** Main Activity */
        private Context context;
        
        /** Groups all this row's display elements */
        private class Packager
        {
            TextView tv;
            ImageView iv;
        }

        /**
         * Calls the default ArrayAdapter constructor
         *
         * @param context reference to the main activity
         */
        public RealtorArrayAdapter(Context context)
        {
            super(context, android.R.layout.simple_list_item_1, realtors);

            this.context = context;
        }

        /**
         * Determines how each row in the main list view showing all the
         *   Realtor's summarized information is displayed
         */
        @Override
        public View getView(int position, View row, ViewGroup parent)
        {
            LayoutInflater inflater;
            Packager packager = new Packager();

            /* sanity check */
            if (realtors == null)
            {
                return null;
            }
    
            /* create this row's view */
            if (row == null)
            {
                if (context == null)
                {
                    return null;
                }
                inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(R.layout.listview_row, parent, false);
                packager.tv = ((TextView) row.findViewById(R.id.textView1));
                packager.iv = ((ImageView) row.findViewById(R.id.imageView1));
                row.setTag(packager);
            }
            
            /* grab an existing row's view */
            else
            {
                packager = (Packager) row.getTag();
            }

            /* update display */
            if (packager.tv != null)
            {
                packager.tv.setText ( String.format (
                        "%s %s",
                        realtors.get(position).name,
                        realtors.get(position).phone_number
                        ));
            }
            if (
                       (packager.iv != null)
                    && (realtors.get(position).thumbnail != null)
                    )
            {
                packager.iv.setImageBitmap(realtors.get(position).thumbnail);
            }

            return row;
        }
    }

    /**
     * @url http://javatechig.com/android/asynchronous-image-loader-in-android-listview
     */
    private class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap>
    {
        /** Realtor we are downloading an image for */
        Realtor realtor;
        
        /** Whether this is the full size bitmap or not */
        boolean isSmall;
        
        /**
         * Automatically update this Realtor's bitmap once download is complete
         */
        public ImageDownloaderTask(Realtor realtor, boolean isSmall)
        {
            this.realtor = realtor;
            this.isSmall = isSmall;
        }

        /**
         * Actual download method, run in the task thread
         * 
         * @param args comes from the execute() call: args[0] is the url.
         */
        @Override
        protected Bitmap doInBackground(String... args)
        {
            final AndroidHttpClient client =
                AndroidHttpClient.newInstance("Android");
            final HttpGet getRequest = new HttpGet(args[0]);
            final HttpResponse response;
            final int statusCode;
            final HttpEntity entity;
            InputStream inputStream = null;

            try
            {
                response = client.execute(getRequest);
                statusCode = response.getStatusLine().getStatusCode();

                if (statusCode != HttpStatus.SC_OK)
                {
                    Log.w ( "ImageDownloader", String.format (
                        "Error %d while retrieving bitmap from %s",
                        statusCode, args[0]
                        ));
                    return null;
                }

                entity = response.getEntity();
                if (entity != null)
                {
                    try
                    {
                        inputStream = entity.getContent();
                        return BitmapFactory.decodeStream(inputStream);
                    }
                    finally
                    {
                        if (inputStream != null)
                        {
                            inputStream.close();
                        }
                        entity.consumeContent();
                    }
                }
            }
            catch (Exception e)
            {
                /* Could provide a more explicit error message for IOException or
                   IllegalStateException */
                getRequest.abort();
                Log.w ( "ImageDownloader", String.format(
                        "Error while retrieving bitmap from %s", args[0]
                        ));
            }
            finally
            {
                if (client != null)
                {
                    client.close();
                }
            }

            return null;
        }

        /**
         * Once the image is downloaded, associates it to the imageView
         */
        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            if (
                   (isCancelled())
                || (bitmap == null)
                || (realtor == null)
                )
            {
                return;
            }

            if (isSmall)
            {
                realtor.thumbnail = bitmap;
                if (adapter != null)
                {
                    adapter.notifyDataSetChanged();
                }
            }
            else
            {
                realtor.portrait = bitmap;
                if (modal != null)
                {
                    modal.dismiss();
                    modal.show();
                }
            }
        }
    }

    /**
     * Handles data retrieved by URLGrabber.
     * 
     * Consider this the main entry point to the program.
     */
    @SuppressLint("HandlerLeak")
    private class URLGrabberHandler extends Handler
    {
        /** Main activity */
        public Activity context = null;

        /**
         * "Main" method
         */
        public void handleMessage(Message msg)
        {
            ListView view = (ListView) findViewById(R.id.listView1);
            JSONObject root;
            JSONArray json_realtors;
            int i;
            Realtor newRealtor;

            /* load retrieved Realtor data into a list */
            realtors = new ArrayList<Realtor>();
            try
            {
                root = new JSONObject((String) msg.obj);
                json_realtors = (JSONArray) root.getJSONArray("realtors");
                for (i = 0; i < json_realtors.length(); i++)
                {
                    newRealtor = new Realtor(json_realtors.getJSONObject(i));
                    realtors.add(newRealtor);
                    new ImageDownloaderTask(newRealtor, true)
                        .execute(String.format("%s/width/50/", newRealtor.photo));
                }
            }
            catch (Exception e)
            {
                return;
            }

            adapter = new RealtorArrayAdapter (context);
            view.setAdapter(adapter);
        }
    }
    
    /**
     * Facilitates transfer of control when a Realtor is clicked on to that
     *   Realtor's detail view
     */
    private class ShowRealtorListener implements OnItemClickListener
    {
        /** Main activity */
        public MainActivity ma = null;
        
        @SuppressLint("NewApi")
        @Override
        public void onItemClick (
                AdapterView<?> arg0, View arg1, int location, long arg3
                )
        {    
            if (ma == null)
            {
                return;
            }
            ma.showModalView(location);
        }
    }
    
    /**
     * Show a modal view going into more detail given a Realtor
     */
    public void showModalView(int location)
    {
        Button button;
        TextView tv;
        ImageView iv;

        if (modal == null)
        {
            return;
        }

        modal.setContentView(R.layout.modal_view);
        modal.setTitle(realtors.get(location).name);
        modal.show();
        
        button = (Button) modal.findViewById(R.id.button1);
        button.setOnClickListener(new OKListener(modal));
        
        tv = (TextView) modal.findViewById(R.id.textView1);
        tv.setText ( String.format (
                "Home office: %s%nPhone number: %s",
                realtors.get(location).office,
                realtors.get(location).phone_number
                ));

        new ImageDownloaderTask(realtors.get(location), false)
            .execute(String.format("%s/width/200/", realtors.get(location).photo));
        if (realtors.get(location).portrait != null)
        {
            iv = (ImageView) modal.findViewById(R.id.imageView1);
            iv.setImageBitmap(realtors.get(location).portrait);
        }
    }
    
    /**
     * Closes the modal view
     */
    private class OKListener implements OnClickListener
    {
        private Dialog modal = null;
        
        public OKListener(Dialog dialog)
        {
            modal = dialog;
        }

        @Override
        public void onClick(View arg0)
        {
            if (modal != null)
            {
                modal.dismiss();
            }
        }
    }

    /**
     * Initializes the app, sets up the loading screen, and starts the bg
     *   thread
     */
    @Override
    protected void onResume()
    {
        super.onResume();

        String[] loadmsg = {"Loading, please wait..."};
        
        /* set up the detail view */
        modal = new Dialog(this);

        /* set up the list view */
        onclick = new ShowRealtorListener();
        onclick.ma = this;
        ((ListView) findViewById(R.id.listView1))
            .setAdapter ( new ArrayAdapter<String> (
                    this, android.R.layout.simple_list_item_1, loadmsg
                    ));
        ((ListView) findViewById(R.id.listView1))
            .setOnItemClickListener(onclick);

        /* set up the background thread to grab remote data */
        mainHandler = new URLGrabberHandler();
        mainHandler.context = this;
        new Thread(new URLGrabber()).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	public void junk() {
		// do nothing
	}
}
