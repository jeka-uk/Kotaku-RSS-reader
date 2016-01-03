package com.example.lenovo.kotakurssreader.objectcache;


import android.content.Context;

import com.example.lenovo.kotakurssreader.common.Constance;
import com.example.lenovo.kotakurssreader.db.DatabaseHelper;
import com.example.lenovo.kotakurssreader.db.RssItemsCache;
import com.example.lenovo.kotakurssreader.db.TimesAddRssNews;
import com.example.lenovo.kotakurssreader.rss.RssItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CacheManager {

    private DatabaseHelper dbHelper;

    public boolean put(List<RssItem> rssItemsNews, Context mContext){
        if(rssItemsNews != null && rssItemsNews.size() > 0){
            for (int i = 0; i <rssItemsNews.size() ; i++) {
                dbHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
                final RuntimeExceptionDao<RssItemsCache, Integer> rssNewsDao = dbHelper.getRssNewsRuntimeExceptionDao();
                rssNewsDao.create(new RssItemsCache(rssItemsNews.get(i).getTitle(), rssItemsNews.get(i).getLinkUrl(), rssItemsNews.get(i).getDescription(), rssItemsNews.get(i).getImageUrl(), rssItemsNews.get(i).getData()));
            }
            setTimestamps(mContext);

        }else{
            return false;
        }
        return true;
    }


    public List<RssItemsCache> get(Context mContext){
        dbHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
        final RuntimeExceptionDao<RssItemsCache, Integer> rssNewsDao = dbHelper.getRssNewsRuntimeExceptionDao();
        return rssNewsDao.queryForAll();
    }


    public boolean clearCahe(Context mContext){
        dbHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
        final RuntimeExceptionDao<RssItemsCache, Integer> rssNewsDao = dbHelper.getRssNewsRuntimeExceptionDao();
        List<RssItemsCache> list = rssNewsDao.queryForAll();
        rssNewsDao.delete(list);
        clearTimestamps(mContext);
        return true;
    }


    private void setTimestamps(Context mContext){
        dbHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
        final RuntimeExceptionDao<TimesAddRssNews, Integer> addTimeNewsDao = dbHelper.getTimesAddRssNewsExceptionDao();
        addTimeNewsDao.create(new TimesAddRssNews());
    }


    private void clearTimestamps(Context mContext){
        dbHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
        final RuntimeExceptionDao<TimesAddRssNews, Integer> addTimeNewsDao = dbHelper.getTimesAddRssNewsExceptionDao();
        List<TimesAddRssNews> list = addTimeNewsDao.queryForAll();
        addTimeNewsDao.delete(list);
    }

    private List<TimesAddRssNews> getTimestamps(Context mContext){
        dbHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
        final RuntimeExceptionDao<TimesAddRssNews, Integer> addTimeNewsDao = dbHelper.getTimesAddRssNewsExceptionDao();
        return addTimeNewsDao.queryForAll();
    }


    public boolean getLustUpdateCache(Context mContext, Date dateNow){
        if(getTimestamps(mContext).size() != 0 && dateNow.getTime() - getTimestamps(mContext).get(0).getDataAdd().getTime() > TimeUnit.MINUTES.toMillis(Constance.TIME_UPDATE_CACHE)){
            return true;
        }
        return false;
    }
}
