package de.aboutyou.mobile.pitservice;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEvent;
import com.adjust.sdk.AdjustEventSuccess;
import com.adjust.sdk.OnEventTrackingSucceededListener;

public class AdjustApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("AdjustApplication", "onCreate");

        final Context context = this;
        AdjustConfig config = new AdjustConfig(this, Constants.APP_TOKEN, Constants.ENVIRONMENT);

        config.setOnEventTrackingSucceededListener(new OnEventTrackingSucceededListener() {
            @Override
            public void onFinishedEventTrackingSucceeded(AdjustEventSuccess eventSuccessResponseData) {
                Log.d("AdjustApplication", "onFinishedEventTrackingSucceeded");
                Adjust.onPause();
                Helpers.setTracked(true, context);
                Helpers.finishActivity(context);
            }
        });

        config.setLogLevel(Constants.LOG_LEVEL);
        Adjust.onCreate(config);

        registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());
    }

    private static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) { }

        @Override
        public void onActivityStarted(Activity activity) { }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.d("AdjustApplication", "onActivityResumed");
            if (!Helpers.isTracked(activity)) {
                Adjust.onResume();
                Adjust.trackEvent(new AdjustEvent(Constants.EVENT_TOKEN));
            } else {
                Helpers.finishActivity(activity);
            }

        }

        @Override
        public void onActivityPaused(Activity activity) { }

        @Override
        public void onActivityStopped(Activity activity) { }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) { }

        @Override
        public void onActivityDestroyed(Activity activity) { }
    }
}