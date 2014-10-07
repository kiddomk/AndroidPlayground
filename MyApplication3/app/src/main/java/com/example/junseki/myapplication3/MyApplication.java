package com.example.junseki.myapplication3;

import android.app.Application;
import android.util.Log;

import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;

/**
 * Created by junseki on 30/09/2014.
 */


public class MyApplication extends Application {
    
    @Override
    public void onCreate(){

        super.onCreate();


        AirshipConfigOptions options = AirshipConfigOptions.loadDefaultOptions(this);

        // Optionally, customize your config at runtime:
        //
        options.inProduction = false;
        options.developmentAppKey = "7YnIpaIlRmO4nGzjVF2pRg";
        options.developmentAppSecret ="yfnYEMkQTvGMY6D4UxwNjA";


        options.gcmSender="644040533376";

        UAirship.takeOff(this, options);
        PushManager.enablePush();
        Logger.logLevel = Log.VERBOSE;

        String apid = PushManager.shared().getAPID();
        Logger.info("My Application onCreate - App APID: " + apid);

        PushManager.shared().setIntentReceiver(IntentReceiver.class);
    }
}
