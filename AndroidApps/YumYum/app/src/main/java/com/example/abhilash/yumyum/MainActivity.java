package com.example.abhilash.yumyum;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_fingerprintText)
    TextView tv_fingerprintText;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private Cipher cipher;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private KeyguardManager keyguardManager;
    private FingerprintManager fingerprintManager;
    private FingerprintManager.CryptoObject cryptoObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        }

        try {
            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(this, getString(R.string.noSensorPresent), Toast.LENGTH_SHORT)
                        .show();
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, getString(R.string.permissionNotGiven), Toast.LENGTH_SHORT)
                    .show();
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            Snackbar.make(coordinatorLayout, getString(R.string.noFingerprintEnrolled), Snackbar.LENGTH_SHORT).show();
        }

        if (!keyguardManager.isDeviceSecure()) {
            Snackbar.make(coordinatorLayout, getString(R.string.deviceNotSecure), Snackbar.LENGTH_LONG).show();
        }

    }
}
