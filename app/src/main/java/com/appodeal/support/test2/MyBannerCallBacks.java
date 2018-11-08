package com.appodeal.support.test2;

import android.util.Log;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;

public class MyBannerCallBacks {

    public static void init(MainActivity mainActivity) {

        Appodeal.setBannerCallbacks(new BannerCallbacks() {

            @Override
            public void onBannerLoaded(int i, boolean b) {
                Log.d("Appodeal", "onBannerLoaded");
            }

            @Override
            public void onBannerFailedToLoad() {
                Log.d("Appodeal", "onBannerFailedToLoad");
            }

            @Override
            public void onBannerShown() {
                Log.d("Appodeal", "onBannerShown");
            }

            @Override
            public void onBannerClicked() {
                Log.d("Appodeal", "onBannerClicked");
            }
        });
    }
}
