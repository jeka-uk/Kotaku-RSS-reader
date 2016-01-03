package com.example.lenovo.kotakurssreader.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.kotakurssreader.R;
import com.example.lenovo.kotakurssreader.common.CommonUtils;
import com.example.lenovo.kotakurssreader.common.SetUrl;
import com.example.lenovo.kotakurssreader.db.RssItemsCache;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RssNewsAdapter extends ArrayAdapter<RssItemsCache> {

    private List<RssItemsCache> mRssItemCache;
    private TextView mTitle, mData, mRedMore;
    private ImageView mImage;
    private SetUrl mSetUrl;


    public RssNewsAdapter(Context context, List<RssItemsCache> mRssItemCache, SetUrl mSetUrl) {
        super(context, 0, mRssItemCache);
        this.mRssItemCache = mRssItemCache;
        this.mSetUrl = mSetUrl;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rss_item_layout, parent, false);
        }

        mTitle = (TextView) convertView.findViewById(R.id.item_name_title);
        mData = (TextView) convertView.findViewById(R.id.item_data);
        mImage = (ImageView) convertView.findViewById(R.id.item_image);
        mRedMore = (TextView) convertView.findViewById(R.id.text_view_read);

        mTitle.setText(mRssItemCache.get(position).getTitle());
        mData.setText(CommonUtils.getDataFormat(mRssItemCache.get(position).getData()));

        Picasso.with(getContext())
                .load(mRssItemCache.get(position).getImageUrl())
                .placeholder(R.drawable.user_placeholder)
                .resize(200, 120)
                .centerCrop()
                .into(mImage);

        mRedMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetUrl.setUrl(mRssItemCache.get(position).getLinkUrl());
            }
        });

        return convertView;
    }
}
