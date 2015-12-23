package com.example.lenovo.kotakurssreader.rss;


import com.example.lenovo.kotakurssreader.common.Constance;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectToWebsite {

    public InputStream connectToWebsite(){
        InputStream stream;
                try {
                    URL url = new URL(Constance.URL_RSS);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setReadTimeout(10000);
                            conn.setConnectTimeout(15000);
                                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    stream = conn.getInputStream();
                    if(stream != null){
                        return stream;
                    }

                    stream.close();
                }

                catch (Exception e) {
                }
                   return null;
            }

}


