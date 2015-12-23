package com.example.lenovo.kotakurssreader.rss;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlPullParserHandler {
    List<RssItem> mRssItem;
    private RssItem mDataRssItem;
    private String text;

    public XmlPullParserHandler() {
        mRssItem = new ArrayList<RssItem>();
    }

    public List<RssItem> getmRssItem() {
        return mRssItem;
    }

    public List<RssItem> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                            mDataRssItem = new RssItem();
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                             mRssItem.add(mDataRssItem);
                         if (tagname.equalsIgnoreCase("title")) {
                            mDataRssItem.setTitle(text);
                             System.out.println("Title "+text);
                        } else if (tagname.equalsIgnoreCase("link")) {
                            mDataRssItem.setImageUrl(text);
                             System.out.println("Link " + text);
                        } else if (tagname.equalsIgnoreCase("description")) {
                            mDataRssItem.setDescription(text);
                            mDataRssItem.setImageUrl("image");
                        } else if (tagname.equalsIgnoreCase("data")) {
                            mDataRssItem.setData(text);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mRssItem;
    }
}
