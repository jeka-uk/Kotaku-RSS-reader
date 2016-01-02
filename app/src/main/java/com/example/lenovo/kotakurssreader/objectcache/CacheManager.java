package com.example.lenovo.kotakurssreader.objectcache;


import android.content.Context;
import com.example.lenovo.kotakurssreader.db.DatabaseHelper;
import com.example.lenovo.kotakurssreader.db.RssItemsCache;
import com.example.lenovo.kotakurssreader.rss.RssItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

public class CacheManager {

    private DatabaseHelper dbHelper;

    public boolean put(List<RssItem> rssItemsNews, Context mContext){
        if(rssItemsNews != null && rssItemsNews.size() > 0){
            for (int i = 0; i <rssItemsNews.size() ; i++) {
                dbHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
                final RuntimeExceptionDao<RssItemsCache, Integer> rssNewsDao = dbHelper.getRssNewsRuntimeExceptionDao();
                rssNewsDao.create(new RssItemsCache(rssItemsNews.get(i).getTitle(), rssItemsNews.get(i).getLinkUrl(), rssItemsNews.get(i).getDescription(), rssItemsNews.get(i).getImageUrl(), rssItemsNews.get(i).getData()));
            }
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

        return true;
    }


}
