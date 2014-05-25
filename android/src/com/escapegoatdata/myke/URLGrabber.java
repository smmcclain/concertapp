package com.escapegoatdata.myke;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.escapegoatdata.myke.Listeners.URLGrabberHandler;

import android.os.Message;
import android.util.Log;

/**
 * Communicates with the JSON server
 *
 * @author Sean McClain <sean.mcclain.dc3.sw at gmail.com>
 */
public class URLGrabber implements Runnable {

    /** main message handler */
    private static URLGrabberHandler mainHandler;
    public static URLGrabberHandler getMainHandler() {
        return mainHandler;
    }
    public static void setMainHandler(URLGrabberHandler newMainHandler) {
        mainHandler = newMainHandler;
    }

    /** location of the content server */
    private static final String CONTENT_URL =
            "http://www.ebby.com/realtor_list?format=ios";

    /**
     * Retrieve raw data from the JSON server
     *
     * @param url incoming URL containing the document to retrieve
     * @return the entire serialized document at the given URL
     */
    String getDataFromURL(String url) {
        HttpEntity entity;
        StringBuilder data = new StringBuilder();
        BufferedReader io;
        String line;

        try {
            if ( (entity = new DefaultHttpClient()
                         .execute(new HttpGet(url))
                         .getEntity()
                    ) == null) {
                return null;
            }
            io = new BufferedReader ( new InputStreamReader (
                    entity.getContent()
                    ));
            while ((line = io.readLine()) != null) {
                data.append(String.format("%s%n", line));
            }
        } catch (Exception e) {
        	Log.w("XXX", e.toString());
            return null;
        }

        Log.w("XXX", data.toString());
        return data.toString();
    }

    /**
     * Attempt to grab data from the server and relay it to the
     *   calling thread
     */
    @Override
    public void run() {
        Message msg = mainHandler.obtainMessage();;
        String result = getDataFromURL(CONTENT_URL);

        if (result != null) {
            msg.obj = result;
            mainHandler.sendMessage(msg);
        }
    }
}