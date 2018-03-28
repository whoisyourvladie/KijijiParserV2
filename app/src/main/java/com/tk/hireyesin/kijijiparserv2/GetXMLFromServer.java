package com.tk.hireyesin.kijijiparserv2;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.InputStream;


    public class GetXMLFromServer extends AsyncTask<String, Void, String> {

        HttpHandler nh;

        @Override
        protected String doInBackground(String... strings) {

            //String URL = "http://androidpala.com/tutorial/horoscope.xml";
            String URL = strings[0];
            String res = "";
            nh = new HttpHandler();
            InputStream is = nh.CallServer(URL);
            if (is != null) {

                res = nh.StreamToString(is);

            } else {
                res = "NotConnected";
            }

            return res;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result.equals("NotConnected")) {

                Toast.makeText(getApplicationContext(), "Not Connected To Server", Toast.LENGTH_SHORT).show();

            } else {
                ParseXML(result);
            }
        }


    }


}
