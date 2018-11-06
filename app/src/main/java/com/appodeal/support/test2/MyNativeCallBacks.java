package com.appodeal.support.test2;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeCallbacks;
import com.appodeal.ads.native_ad.views.NativeAdViewNewsFeed;

public class MyNativeCallBacks {
    public static void init(final MainActivity act) {
        Appodeal.cache(act, Appodeal.NATIVE);
        Appodeal.cache(act, Appodeal.NATIVE, 3);
        Appodeal.getNativeAds(3);
        Appodeal.setNativeCallbacks(new NativeCallbacks() {
            @Override
            public void onNativeLoaded() {

            }

            @Override
            public void onNativeFailedToLoad() {

            }

            @Override
            public void onNativeShown(NativeAd nativeAd) {

                Appodeal.hide(act, Appodeal.BANNER_TOP);
            }

            @Override
            public void onNativeClicked(NativeAd nativeAd) {

            }
        });

    }
}
