package com.example.lenovo.kotakurssreader.db;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "rssItem")
public class RssItemsCache {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String title;

    @DatabaseField
    private String linkUrl;

    @DatabaseField
    private String description;

    @DatabaseField
    private String imageUrl;

    @DatabaseField
    private String data;


    public RssItemsCache(){ }

    public RssItemsCache(String title, String linkUrl, String description, String imageUrl, String data) {
        this.title = title;
        this.linkUrl = linkUrl;
        this.description = description;
        this.imageUrl = imageUrl;
        this.data = data;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
