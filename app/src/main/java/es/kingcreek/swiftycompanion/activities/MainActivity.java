package es.kingcreek.swiftycompanion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Properties;

import es.kingcreek.swiftycompanion.R;
import es.kingcreek.swiftycompanion.api42.API;
import es.kingcreek.swiftycompanion.api42.interfaces.OnLoginListener;
import es.kingcreek.swiftycompanion.api42.interfaces.OnUserInfoListener;
import es.kingcreek.swiftycompanion.api42.responses.UserInfoResponse;
import es.kingcreek.swiftycompanion.constants.Constants;
import es.kingcreek.swiftycompanion.helper.PreferencesManager;

public class MainActivity extends AppCompatActivity implements OnUserInfoListener, OnLoginListener {

    private final String TAG = "MainActivity";
    private API api;
    private PreferencesManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = API.getInstance(this);
        preferences = PreferencesManager.getInstance(this);

        // Check if app have access token
        if (!api.haveAutorizationCode()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            String authorization_code = preferences.getAutorizationCode();
            Log.e(TAG, "authorization_code: " + authorization_code);
            api.login(authorization_code, Constants.CLIENT_SECRET, this);
        }
    }

    @Override
    public void onUserInfoSuccess(UserInfoResponse userInfo) {
        Log.e(TAG, "success: " + userInfo.getImage().getLink());
    }

    @Override
    public void onUserInfoFailure(String errorMessage) {
        Log.e(TAG, "onUserInfoFailure:" + errorMessage);
        api.login(preferences.getAccessToken(), Constants.CLIENT_SECRET, null);
        //getUserInfo(accessToken, listener, retryCount - 1);
    }

    @Override
    public void onLoginSuccess(String accessToken) {
        preferences.setAccessToken(accessToken);
        api.getUserInfo(accessToken, this);
    }

    @Override
    public void onLoginFailure(String message) {
        Log.e(TAG, "onLoginFailure:" + message);
    }
}