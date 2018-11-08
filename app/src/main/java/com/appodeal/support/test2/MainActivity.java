package com.appodeal.support.test2;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.native_ad.views.NativeAdViewNewsFeed;

import static com.mobvista.msdk.out.MVConfiguration.LOG_TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBanners;
    Button btnInterstitials;
    Button btnRewardedVideo;
    Button btnNative;

    public static int adDelay = 30;
    public CountDownTimer cdt;
    int counterBanners;
    int counterVideos;
    int coins;
    public static Boolean interstitialPermissionOn = false ;
    public int reward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBanners = (Button) findViewById(R.id.btnBanners);
        btnBanners.setOnClickListener(this);
        btnInterstitials = (Button) findViewById(R.id.btnInterstitials);
        btnInterstitials.setOnClickListener(this);
        btnInterstitials.setEnabled(false);
        btnRewardedVideo = (Button) findViewById(R.id.btnRewardedVideo);
        btnRewardedVideo.setOnClickListener(this);
        btnNative = (Button) findViewById(R.id.btnNative);
        btnNative.setOnClickListener(this);

        String appKey = "348cfe0b0e8eaa3da8d440e983a55be1dfe0ab5c1248079f";
        Appodeal.disableLocationPermissionCheck();
        Appodeal.initialize(this, appKey, Appodeal.INTERSTITIAL | Appodeal.REWARDED_VIDEO | Appodeal.BANNER_TOP | Appodeal.NATIVE);

        btnInterstitials.setEnabled(false);;
        MyInterstitialsCallBacks.init(this);

        btnRewardedVideo.setEnabled(false);
        MyRewardedVideoCallBacks.init(this);

        btnNative.setEnabled(false);
        MyNativeCallBacks.init(this);

        MyBannerCallBacks.init(this);

        this.cdt = new CountDownTimer(adDelay * 1000, 1000) {

            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                interstitialPermissionOn = true;
            }
        }.start();

    }


    @Override
    public void onResume() {
        super.onResume();
        Appodeal.onResume(this, Appodeal.BANNER_TOP);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBanners:
                Appodeal.show(this, Appodeal.BANNER_TOP);
                counterBanners++;
                if (counterBanners >= 7 && interstitialPermissionOn == true ) {
                    btnInterstitials.setEnabled(true);
                }

                break;
            case R.id.btnInterstitials:
                if (interstitialPermissionOn) {
                    Appodeal.show(this, Appodeal.INTERSTITIAL);
                    interstitialPermissionOn = false;
                    btnInterstitials.setEnabled(false);
                    cdt.start();
                }
                break;
            case R.id.btnRewardedVideo:
                counterVideos++;
                coins += 10;
                if (counterVideos >= 3) {
                    btnNative.setEnabled(true);
                }
                Appodeal.show(this,Appodeal.REWARDED_VIDEO);
                Toast toast = Toast.makeText(this, "You received " + coins + " coins",Toast.LENGTH_LONG);
                toast.show();
                break;
            case R.id.btnNative:
                Appodeal.hide(this, Appodeal.BANNER_TOP);
                findViewById(R.id.listView).setVisibility(View.VISIBLE);
                Appodeal.show(this,Appodeal.NATIVE);
                break;
        }
    }
}
