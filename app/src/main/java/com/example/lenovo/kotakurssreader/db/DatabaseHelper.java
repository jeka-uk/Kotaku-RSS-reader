package com.example.lenovo.kotakurssreader.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lenovo.kotakurssreader.common.Constance;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = Constance.NAME_LOCAL_DB;

    private RuntimeExceptionDao<RssItemsCache, Integer> rssItemRuntimeDAO = null;
    private RuntimeExceptionDao<TimesAddRssNews, Integer> timesAddRssNewsDAO = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, Constance.DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, RssItemsCache.class);
            TableUtils.createTable(connectionSource, TimesAddRssNews.class);
        } catch (SQLException e){
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            TableUtils.dropTable(connectionSource, RssItemsCache.class, true);
            TableUtils.dropTable(connectionSource, TimesAddRssNews.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }


    public RuntimeExceptionDao<RssItemsCache, Integer> getRssNewsRuntimeExceptionDao(){
        if(rssItemRuntimeDAO == null){
            rssItemRuntimeDAO = getRuntimeExceptionDao(RssItemsCache.class);
        }
        return rssItemRuntimeDAO;
    }


    public RuntimeExceptionDao<TimesAddRssNews, Integer> getTimesAddRssNewsExceptionDao(){
        if(timesAddRssNewsDAO == null){
            timesAddRssNewsDAO = getRuntimeExceptionDao(TimesAddRssNews.class);
        }
        return timesAddRssNewsDAO;
    }


    @Override
    public void close() {
        super.close();
        rssItemRuntimeDAO = null;
        timesAddRssNewsDAO = null;
    }
}
