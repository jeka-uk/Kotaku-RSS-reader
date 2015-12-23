package com.example.lenovo.kotakurssreader.rss;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Deprecated
public class HandleXML {

    /*private String title = "title";
    private String link = "link";
    private String description = "description";
    private String urlString = null;
    private String pubDate = "pubDate";
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;


    public HandleXML(String url){
        this.urlString = url;
    }

    public String getTitle(){
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLink(){
        return link;
    }

    public String getDescription(){
        return description;
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text=null;
        String result = null;
        String href="";

        try {
            event = myParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();

                switch (event){
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if(name.equals("title")){
                            title = text;
                            System.out.println("title " + title);

                        }

                        else if(name.equals("link")){
                            link = text;
                            System.out.println("link " + link);


                        }

                        else if(name.equals("description")){
                            description = text;

                            System.out.println("description "+description);

                        }else if(name.equals("pubDate")){
                            pubDate = text;
                            System.out.println("pubDate " + pubDate);
                        }
                        break;
                }

                event = myParser.next();
            }

            parsingComplete = false;
        }

        catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setReadTimeout(10000 *//* milliseconds *//*);
                    conn.setConnectTimeout(15000 *//* milliseconds *//*);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);

                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    parseXMLAndStoreIt(myparser);
                    stream.close();
                }

                catch (Exception e) {
                }
            }
        });
        thread.start();
    }*/
}
