package es.kingcreek.swiftycompanion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Properties;

import es.kingcreek.swiftycompanion.R;
import es.kingcreek.swiftycompanion.api42.API;
import es.kingcreek.swiftycompanion.api42.interfaces.OnLoginListener;
import es.kingcreek.swiftycompanion.api42.interfaces.OnUserInfoListener;
import es.kingcreek.swiftycompanion.api42.responses.UserInfoResponse;
import es.kingcreek.swiftycompanion.constants.Constants;
import es.kingcreek.swiftycompanion.helper.PreferencesManager;
import es.kingcreek.swiftycompanion.views.CustomProgressBar;

public class MainActivity extends AppCompatActivity implements OnUserInfoListener {

    private final String TAG = "MainActivity";
    private API api;
    private PreferencesManager preferences;

    // Views
    ImageView profileImageView;
    CustomProgressBar customProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = API.getInstance(this);
        preferences = PreferencesManager.getInstance(this);

        //views
        profileImageView = findViewById(R.id.profileImageView);
        customProgressBar = findViewById(R.id.customProgressBar);


        // Check if app have access token
        if(!api.haveAccessCode())
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            api.getUserInfo(preferences.getAccessToken(), this);
        }

    }

    @Override
    public void onUserInfoSuccess(UserInfoResponse userInfo) {
        Log.e(TAG, "success: " + userInfo.getImage().getLink());
        populateUserData(userInfo);
    }

    @Override
    public void onUserInfoFailure(String errorMessage) {
        Log.e(TAG, "onUserInfoFailure:" + errorMessage);
        // Delete tokens
        preferences.setAccessToken(null);
        //preferences.setAutorizationCode(null);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.putExtra("newLogin", true);
        startActivity(intent);
        finish();
        //api.login(preferences.getAccessToken(), Constants.CLIENT_SECRET, null);
        //getUserInfo(accessToken, listener, retryCount - 1);
    }

    private void populateUserData(UserInfoResponse user)
    {
        // Load image
        Glide.with(this).load(user.getImage().getLink()).into(profileImageView);

        // Load bar level
        customProgressBar.setProgress(50);
        customProgressBar.setBarColor(Color.GREEN);
        customProgressBar.setCenterText("Hello");
    }
}