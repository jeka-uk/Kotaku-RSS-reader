package com.example.lenovo.kotakurssreader.adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.kotakurssreader.R;
import com.example.lenovo.kotakurssreader.common.CommonUtils;
import com.example.lenovo.kotakurssreader.rss.RssItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

public class RssNewsAdapter extends ArrayAdapter<RssItem> {

    private List<RssItem> mRssItem;
    private TextView mTitle, mData, mRedMore;
    private ImageView mImage;


    public RssNewsAdapter(Context context, List<RssItem> rssItem) {
        super(context, 0, rssItem);
        this.mRssItem = rssItem;
    }


    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.drawable.user_placeholder)
            .resetViewBeforeLoading()
            .cacheInMemory()
            .cacheOnDisc()
            .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
            .build();

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rss_item_layout, parent, false);
        }

        mTitle = (TextView) convertView.findViewById(R.id.item_name_title);
        mData = (TextView) convertView.findViewById(R.id.item_data);
        mImage = (ImageView) convertView.findViewById(R.id.item_image);
        mRedMore = (TextView) convertView.findViewById(R.id.text_view_read);

        mTitle.setText(mRssItem.get(position).getTitle());
        mData.setText(CommonUtils.getDataFormat(mRssItem.get(position).getData()));

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
        imageLoader.displayImage(mRssItem.get(position).getImageUrl(), mImage, options);

        mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(mRssItem.get(position).getLinkUrl()));
                getContext().startActivity(newIntent);
            }
        });

        return convertView;
    }
}
