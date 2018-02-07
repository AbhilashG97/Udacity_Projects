package com.example.abhilash.yumyum;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.mattprecious.swirl.SwirlView;

/**
 * Created by root on 8/2/18.
 */

class FingerprintHandler extends FingerprintManager.AuthenticationCallback{


    private CancellationSignal cancellationSignal;
    private Context mContext;
    private View mCoordinatorLayout;
    private SwirlView mSwirlView;

    public FingerprintHandler(Context context,
                              CoordinatorLayout coordinatorLayout,
                              SwirlView swirlView){
        mContext = context;
        mCoordinatorLayout = coordinatorLayout;
        mSwirlView = swirlView;
    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){
        cancellationSignal = new CancellationSignal();
        if(ActivityCompat.checkSelfPermission(mContext, Manifest.permission.USE_FINGERPRINT) !=
                PackageManager.PERMISSION_GRANTED){
            return;
        }
        mSwirlView.setState(SwirlView.State.ON);
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsg,
                                      CharSequence errString){
        Snackbar.make(mCoordinatorLayout, "Authentication error\n"+errString,
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationFailed(){
        mSwirlView.setState(SwirlView.State.ERROR);
        Snackbar.make(mCoordinatorLayout, R.string.authentication_failed,
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationHelp(int messageID,
                                     CharSequence helpString){
        Snackbar.make(mCoordinatorLayout, helpString, Snackbar.LENGTH_LONG);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult){
        mSwirlView.setState(SwirlView.State.ON);
        Snackbar.make(mCoordinatorLayout, R.string.success, Snackbar.LENGTH_SHORT).show();
    }
}
