package com.example.lenovo.kotakurssreader.common;


import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.lenovo.kotakurssreader.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    public static void startFragmentSlideVerticalDownUp(Fragment newFragment, int containerViewId, FragmentManager fragmentManager){
        FragmentTransaction frTra = fragmentManager.beginTransaction();
        frTra.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_in_down);
        frTra.replace(containerViewId, newFragment);
        //frTra.addToBackStack(null);
        frTra.commit();
    }

    public static void startFragmentSlideVerticalDownUpWithBackStack(Fragment newFragment, int containerViewId, FragmentManager fragmentManager){
        FragmentTransaction frTra = fragmentManager.beginTransaction();
        frTra.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_in_down);
        frTra.add(containerViewId, newFragment);
        frTra.addToBackStack("web");
        frTra.commit();
    }



    public static String getDataFormat(String data){
        return data.substring(data.indexOf("The,")+5, data.indexOf("GMT")-1);
    }


    public static String getImageUrl(String data){
        Pattern pattern = Pattern.compile( "(?m)(?s)<img\\s+(.*)src\\s*=\\s*\"([^\"]+)\"(.*)" );
        Matcher matcher = pattern.matcher(data);
        String src = null;
        if( matcher.matches()) {
            src = matcher.group(2);
        }
        return src;
    }


    public static void startFragmentTraspered(Fragment newFragment, int containerViewId, FragmentManager fragmentManager){
        FragmentTransaction frTra = fragmentManager.beginTransaction();
        //frTra.setCustomAnimations(R.anim.slide_in_down, R.anim.slide_in_up);
        frTra.replace(containerViewId, newFragment);
        frTra.addToBackStack("transpare");
        frTra.commit();
    }





    /* Matcher matcher=Pattern.compile("<a\\b.*?\\shref\\s*=\\s*(\"|')(.*?)(\\1)").matcher(description);
                            while (matcher.find())
                            System.out.println("Href: " + matcher.group(2));*/

    /*Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(description);
    while (matcher.find()) {
        System.out.println("img url: " + matcher.group(1));
    }*/

}
