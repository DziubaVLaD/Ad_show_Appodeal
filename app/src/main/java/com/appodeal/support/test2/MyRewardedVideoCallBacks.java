package com.appodeal.support.test2;

import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.RewardedVideoCallbacks;

public class MyRewardedVideoCallBacks {
    public static void init(final MainActivity act) {
        Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
            @Override
            public void onRewardedVideoLoaded() {
                Log.d("Appodeal", "onRewardedVideoLoaded");
                TextView txtVIew = act.findViewById(R.id.txtViewVideoNotLoaded);
                txtVIew.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onRewardedVideoFailedToLoad() {
                Log.d("Appodeal", "onRewardedVideoFailedToLoad");
                act.btnRewardedVideo.setEnabled(false);
            }
            @Override
            public void onRewardedVideoShown() {
                Log.d("Appodeal", "onRewardedVideoShown");
                Appodeal.hide(act, Appodeal.BANNER_TOP);
            }
            @Override
            public void onRewardedVideoFinished(int amount, String name) {
                Log.d("Appodeal", "onRewardedVideoFinished");
                Pair pair = Appodeal.getRewardParameters("coins");
                System.out.println(pair);

            }
            @Override
            public void onRewardedVideoClosed(boolean finished) {
                Log.d("Appodeal", "onRewardedVideoClosed");
                Appodeal.show(act, Appodeal.BANNER_TOP);
            }
        });

    }
}
