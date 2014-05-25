package com.escapegoatdata.myke;

import org.json.JSONObject;

import android.graphics.Bitmap;

/**
 * Contains singleton objects for storing retrieved JSON data
 *
 * @author Sean McClain <sean.mcclain.dc3.sw at gmail.com>
 */
public class JSONObjects {

    /** Singleton instances */
    public static final Realtor realtor = new Realtor();

    /**
     * JSON object representing a Realtor
     */
    public static class Realtor {
        private String name;
        private int id;
        private String rebrand;
        private String office;
        private String phone_number;
        private String photo;
        public Bitmap thumbnail = null;
        public Bitmap portrait = null;

        public void setData(JSONObject obj) {
            try {
                name = obj.getString("name");
                id = obj.getInt("id");
                rebrand = obj.getString("rebrand");
                office = obj.getString("office");
                phone_number = obj.getString("phone_number");
                photo = obj.getString("photo");
            } catch (Exception e) {
            }
        }
    }

}