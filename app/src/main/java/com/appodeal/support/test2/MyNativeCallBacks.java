package com.appodeal.support.test2;

import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeCallbacks;
import com.appodeal.ads.native_ad.views.NativeAdViewNewsFeed;

import java.util.ArrayList;
import java.util.HashMap;

import static android.provider.MediaStore.MediaColumns.TITLE;
import static android.provider.MediaStore.Video.VideoColumns.DESCRIPTION;

public class MyNativeCallBacks {
    public static void init(final MainActivity act) {
        Appodeal.setAutoCacheNativeIcons(true);
        Appodeal.setAutoCacheNativeMedia(false);

        Appodeal.cache(act, Appodeal.NATIVE);
        Appodeal.cache(act, Appodeal.NATIVE, 1);
        Appodeal.getNativeAds(1);
        Appodeal.setAutoCacheNativeIcons(true); //нативная реклама
        Appodeal.setAutoCacheNativeMedia(false);

        ListView listView = (ListView) act.findViewById(R.id.listView);

        // создаем массив списков
        ArrayList<HashMap<String, Object>> adList = new ArrayList<>();

        HashMap<String, Object> hashMap;

        hashMap = new HashMap<>();
        hashMap.put(TITLE, "Новость 1"); // Название
        hashMap.put(DESCRIPTION, "Описание 1"); // Описание

        adList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(TITLE, "Новость 2"); // Название
        hashMap.put(DESCRIPTION, "Описание 2"); // Описание

        adList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(TITLE, "Новость 3"); // Название
        hashMap.put(DESCRIPTION, "Описание 3"); // Описание

        adList.add(hashMap);

        listView.setVisibility(View.INVISIBLE);

        SimpleAdapter adapter = new SimpleAdapter(act, adList,
                R.layout.list_item, new String[]{TITLE, DESCRIPTION,},
                new int[]{R.id.native_title, R.id.native_description});

        // Устанавливаем адаптер для списка
        listView.setAdapter(adapter);


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
          //нативная реклама
            Log.d("Appodeal", "showNativeAd");
            ConstraintLayout nativeView = act.findViewById(R.id.native_item);
            NativeAd nativeAd;
            try {
                nativeAd = Appodeal.getNativeAds(1).get(0);
          } catch (IndexOutOfBoundsException i) {
              Log.d("Appodeal", "No more ads available now.");
              return;
          }

            TextView nativeAdSign = nativeView.findViewById(R.id.native_ad_sign);
            nativeAdSign.setText("Ad");

            TextView nativeTitle = nativeView.findViewById(R.id.native_title);
            nativeTitle.setText(nativeAd.getTitle());

            TextView nativeDescription = nativeView.findViewById(R.id.native_description);
            nativeDescription.setMaxLines(3);
            nativeDescription.setEllipsize(TextUtils.TruncateAt.END);
            nativeDescription.setText(nativeAd.getDescription());

            ((ImageView) nativeView.findViewById(R.id.native_image)).setImageBitmap(nativeAd.getImage());

            View providerView = nativeAd.getProviderView(act);
            if (providerView != null) {
                FrameLayout providerViewContainer = nativeView.findViewById(R.id.native_provider_view);
                providerViewContainer.addView(providerView);
            }

            nativeAd.registerViewForInteraction(nativeView);
            nativeView.setVisibility(View.VISIBLE);

    }

}
