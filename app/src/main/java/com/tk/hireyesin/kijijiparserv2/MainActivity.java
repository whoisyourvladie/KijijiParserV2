package com.tk.hireyesin.kijijiparserv2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.StringReader;

public class MainActivity extends Activity {

    private String LOG_TAG = "XML";
    private TextView linkTV;
    private ListView listLW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkTV=findViewById(R.id.linkTV);
        listLW=findViewById(R.id.listLW);
        //new GetXMLFromServer().execute(linkTV.getText().toString());
    }


    public void onGetClick(View v){
        new GetXMLFromServer().execute(linkTV.getText().toString());


    }

    public void ParseXML(String xmlString) {

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));
            Document doc=parser.getD
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("employee")) {
                            // create a new instance of employee
                            employee = new Employee();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("employee")) {
                            // add employee object to list
                            employees.add(employee);
                        }else if (tagname.equalsIgnoreCase("id")) {
                            employee.setId(Integer.parseInt(text));
                        }  else if (tagname.equalsIgnoreCase("name")) {
                            employee.setName(text);
                        } else if (tagname.equalsIgnoreCase("salary")) {
                            employee.setSalary(Float.parseFloat(text));
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();

            }


        } catch (Exception e) {
            Log.d(LOG_TAG, "Error in ParseXML()", e);
        }

    }




}
