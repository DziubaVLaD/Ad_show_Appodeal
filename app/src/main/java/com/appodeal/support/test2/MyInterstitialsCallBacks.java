package com.appodeal.support.test2;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;

public class MyInterstitialsCallBacks {
    public static void init(final MainActivity act){
        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            @Override
            public void onInterstitialLoaded(boolean b) {
                act.btnRewardedVideo.setEnabled(true);
            }

            @Override
            public void onInterstitialFailedToLoad() {
                act.btnRewardedVideo.setEnabled(false);
            }

            @Override
            public void onInterstitialShown() {
                Appodeal.hide(act, Appodeal.BANNER_TOP);
            }

            @Override
            public void onInterstitialClicked() {

            }

            @Override
            public void onInterstitialClosed() {
                Appodeal.show(act, Appodeal.BANNER_TOP);
            }
        });
    }
}
