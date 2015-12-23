package com.example.lenovo.kotakurssreader.common;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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


    /* Matcher matcher=Pattern.compile("<a\\b.*?\\shref\\s*=\\s*(\"|')(.*?)(\\1)").matcher(description);
                            while (matcher.find())
                            System.out.println("Href: " + matcher.group(2));*/

    /*Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(description);
    while (matcher.find()) {
        System.out.println("img url: " + matcher.group(1));
    }*/

}
