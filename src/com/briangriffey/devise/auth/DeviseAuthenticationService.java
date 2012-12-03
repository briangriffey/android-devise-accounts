package com.briangriffey.devise.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * class created by briangriffey
 */
public class DeviseAuthenticationService extends Service {

    private DeviseAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        mAuthenticator = new DeviseAuthenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
       return mAuthenticator.getIBinder();
    }
}
