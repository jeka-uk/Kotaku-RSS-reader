package com.example.lenovo.kotakurssreader.db;

import android.util.Log;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.SimpleDateFormat;
import java.util.Date;

@DatabaseTable(tableName = "timestamps")
public class TimesAddRssNews {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private Date dataAdd;

    public TimesAddRssNews(){
        this.dataAdd = getDate();
    }

    public Date getDataAdd() {
        return dataAdd;
    }

    public void setDataAdd(Date dataAdd) {
        this.dataAdd = dataAdd;
    }

    private Date getDate(){
        Date date = new Date();
        return date;
    }
}
