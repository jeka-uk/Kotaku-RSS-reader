package com.example.lenovo.kotakurssreader.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.lenovo.kotakurssreader.R;

import java.util.Properties;


public class WebBrowserFragment extends Fragment{

    private WebView mWebView;
    private String mUrl;
    private ProgressBar mProgress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_web_layout, container, false);
        mWebView = (WebView) view.findViewById(R.id.web_view);
        mProgress = (ProgressBar) view.findViewById(R.id.progress_barr);

        mWebView.getSettings().setJavaScriptEnabled(true);
        startWeb(mWebView, mUrl);


        return view;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }


    private void startWeb(WebView webWiew, String url){
        webWiew.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                if (mProgress.getVisibility() == View.VISIBLE) {
                    mProgress.setVisibility(View.GONE);
                }
            }
        });
        webWiew.loadUrl(url);
    }


    @Override
    public void onPause() {
        super.onPause();
        mWebView.stopLoading();
    }
}
