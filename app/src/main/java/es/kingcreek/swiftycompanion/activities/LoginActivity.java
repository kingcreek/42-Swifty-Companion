package es.kingcreek.swiftycompanion.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import es.kingcreek.swiftycompanion.R;
import es.kingcreek.swiftycompanion.api42.API;
import es.kingcreek.swiftycompanion.api42.interfaces.OnLoginListener;
import es.kingcreek.swiftycompanion.constants.Constants;
import es.kingcreek.swiftycompanion.helper.PreferencesManager;

public class LoginActivity extends AppCompatActivity {

    final String TAG = "LoginActivity";
    private API api;
    private PreferencesManager preferences;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        api = API.getInstance(this);
        preferences = PreferencesManager.getInstance(this);

        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, WebViewActivity.class);
            startActivityForResult(intent, Constants.WEBVIEW_RESULT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.WEBVIEW_RESULT) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                String authorization_code = data.getStringExtra("authorization_code");
                preferences.setAutorizationCode(authorization_code);
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
}