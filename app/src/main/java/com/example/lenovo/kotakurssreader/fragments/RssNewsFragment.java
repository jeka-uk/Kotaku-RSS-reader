package com.example.lenovo.kotakurssreader.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.lenovo.kotakurssreader.R;
import com.example.lenovo.kotakurssreader.adapters.RssNewsAdapter;
import com.example.lenovo.kotakurssreader.common.CommonUtils;
import com.example.lenovo.kotakurssreader.common.Constance;
import com.example.lenovo.kotakurssreader.common.OnClickTransparent;
import com.example.lenovo.kotakurssreader.common.SetUrl;
import com.example.lenovo.kotakurssreader.db.RssItemsCache;
import com.example.lenovo.kotakurssreader.objectcache.CacheManager;
import com.example.lenovo.kotakurssreader.rss.RssHandler;
import com.example.lenovo.kotakurssreader.rss.RssItem;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;


public class RssNewsFragment extends Fragment implements OnClickTransparent, SetUrl {

    private ListView mRssListView;
    private RssNewsAdapter mRssNewsAdapter;
    private View mView;
    private FloatingActionsMenu mMainButtonMenu;
    private FloatingActionButton mSortUp, mSortDown;
    private boolean test = false;
    private CacheManager mCacheManager = new CacheManager();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rss_news, container, false);
        setHasOptionsMenu(true);


        mRssListView = (ListView) view.findViewById(R.id.result_list_view);
        mView = getActivity().getLayoutInflater().inflate(R.layout.heder_layout,null);
        mRssListView.addHeaderView(mView);
        mMainButtonMenu = (FloatingActionsMenu) view.findViewById(R.id.multiple_actions_up);
        mMainButtonMenu.setOnFloatingActionsMenuUpdateListener(onTransparent);
        mSortDown = (FloatingActionButton) view.findViewById(R.id.sort_down);
        mSortDown.setColorNormal(Color.WHITE);
        mSortDown.setOnClickListener(onSortDown);
        mSortUp = (FloatingActionButton) view.findViewById(R.id.sort_up);
        mSortUp.setColorNormal(Color.WHITE);
        mSortUp.setOnClickListener(onSortUp);

        if(!isNetworkAvailable(getActivity()) && mCacheManager.get(getActivity()).size() != 0){
            setRssItem(mCacheManager.get(getActivity()));
        }else{
            new LoadXml().execute(new String[]{Constance.URL_RSS});
        }

        return view;
    }



    View.OnClickListener onSortDown = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mMainButtonMenu.collapse();
            mRssNewsAdapter.sort(new Comparator<RssItemsCache>() {
                @Override
                public int compare(RssItemsCache lhs, RssItemsCache rhs) {
                    return rhs.getData().compareTo(lhs.getData());
                }
            });
        }
    };


    View.OnClickListener onSortUp = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mMainButtonMenu.collapse();
            mRssNewsAdapter.sort(new Comparator<RssItemsCache>() {
                @Override
                public int compare(RssItemsCache lhs, RssItemsCache rhs) {
                    return lhs.getData().compareTo(rhs.getData());
                }
            });
        }
    };


    private void startTranspa(){
        test = true;
        TransparentColorFragment newFragment = new TransparentColorFragment();
        newFragment.setmCloseTranspare(this);
        CommonUtils.startFragmentTraspered(newFragment, R.id.transparent_color_fragment, getFragmentManager());
    }


    FloatingActionsMenu.OnFloatingActionsMenuUpdateListener onTransparent = new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
        @Override
        public void onMenuExpanded() {
            startTranspa();
        }

        @Override
        public void onMenuCollapsed() {
            getFragmentManager().popBackStack();
        }
    };


    @Override
    public void onStart() {
        super.onStart();
        if(!isNetworkAvailable(getActivity())){
            Log.i(getClass().getName(), "Network off");
            informationConnection();
        }
    }


    private void informationConnection(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.title_information_network)
                .setMessage(R.string.message_information_network)
                .setCancelable(false)
                .setPositiveButton(R.string.button_ok_information_network, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create().show();
    }


    public boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


    private void setRssItem(List<RssItemsCache> mRssItem){
        mRssListView.setVisibility(View.VISIBLE);
        mRssNewsAdapter = new RssNewsAdapter(getActivity(), mRssItem, this);
        mRssListView.setAdapter(mRssNewsAdapter);
    }


    @Override
    public void onClick() {
        mMainButtonMenu.collapse();
    }


    @Override
    public void setUrl(String url) {
        if(url != null){
            WebBrowserFragment mWebBrowserFragment = new WebBrowserFragment();
            mWebBrowserFragment.setUrl(url);
            CommonUtils.startFragmentSlideVerticalDownUpWithBackStack(mWebBrowserFragment, R.id.container, getFragmentManager());
        }
    }


    private class LoadXml extends AsyncTask<String, Void, List<RssItem>> {
        @Override
        protected List<RssItem> doInBackground(String... params) {
            String feed = params[0];
            URL url = null;
            try {
                SAXParserFactory spf = SAXParserFactory.newInstance();
                SAXParser sp = spf.newSAXParser();
                XMLReader xr = sp.getXMLReader();

                url = new URL(feed);
                RssHandler rh = new RssHandler();
                xr.setContentHandler(rh);

                xr.parse(new InputSource(url.openStream()));

                return rh.getRssItemList();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(final List<RssItem> items) {
            if (items != null){
                Log.i(this.getClass().getSimpleName(), items.size() + "");
                Log.i("Create", String.valueOf(items.size()));
                mCacheManager.clearCahe(getActivity());
                if(mCacheManager.put(items, getActivity()))
                    setRssItem(mCacheManager.get(getActivity()));
            }
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh:
                mMainButtonMenu.collapse();
                if(isNetworkAvailable(getActivity())) {
                    new LoadXml().execute(new String[]{Constance.URL_RSS});
                }else{
                    informationConnection();
                }
                return true;
        }

        return false;
    }
}





