package com.example.lenovo.kotakurssreader.rss;

import android.util.Log;

import com.example.lenovo.kotakurssreader.common.CommonUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RssHandler extends DefaultHandler {

    private RssItem currentRssItem = new RssItem();
    private List<RssItem> rssItemList = new ArrayList<RssItem>();
    private static final int ARTICLES_LIMIT = 15;
    StringBuffer chars = new StringBuffer();

    public List<RssItem> getRssItemList() {
        return rssItemList;
    }


    public void startElement(String uri, String localName, String qName, Attributes atts) {
        if (localName.equalsIgnoreCase("item")) {
            currentRssItem = new RssItem();
            rssItemList.add(currentRssItem);
        }
        Log.i(this.getClass().getSimpleName(), localName);
        Log.i(this.getClass().getSimpleName(), qName);
        chars = new StringBuffer();
    }


    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equalsIgnoreCase("title")){
            currentRssItem.setTitle(chars.toString());
        } else if (localName.equalsIgnoreCase("description")){
            currentRssItem.setDescription(chars.toString());
            currentRssItem.setImageUrl(CommonUtils.getImageUrl(chars.toString()));
        } else if (localName.equalsIgnoreCase("link")){
            currentRssItem.setLinkUrl(chars.toString());
        } else if (localName.equalsIgnoreCase("pubDate")){
            currentRssItem.setData(chars.toString());
        }
    }


    public void characters(char ch[], int start, int length) {
        chars.append(new String(ch, start, length));
    }
}