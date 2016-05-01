package de.aboutyou.mobile.pitservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "onCreate");

        BroadcastReceiver finishReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("finishReceiver", "onReceive");
                unregisterReceiver(this);
                finish();
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.FINISH_ACTION);
        registerReceiver(finishReceiver, filter);

        moveTaskToBack(true);
    }
}
