package com.example.lenovo.kotakurssreader.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.lenovo.kotakurssreader.R;
import com.example.lenovo.kotakurssreader.common.Constance;
import com.example.lenovo.kotakurssreader.rss.ConnectToWebsite;
import com.example.lenovo.kotakurssreader.rss.HandleXML;
import com.example.lenovo.kotakurssreader.rss.RssItem;
import com.example.lenovo.kotakurssreader.rss.XmlPullParserHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class RssNewsFragment extends Fragment{

    private ConnectToWebsite mConnectToWebsite = new ConnectToWebsite();
    private XmlPullParserHandler mXmlPullParserHandler = new XmlPullParserHandler();
    private InputStream stream;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rss_news, container, false);


        connect();

        return view;
    }


    private void connect() {

        List<RssItem> mRssItem = null;

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                stream = mConnectToWebsite.connectToWebsite();

                if(stream != null){
                    mXmlPullParserHandler.parse(stream);
                }

            }
        });
        thread.start();
        }
    }



