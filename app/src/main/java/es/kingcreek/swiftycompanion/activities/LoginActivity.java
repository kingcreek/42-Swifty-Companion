package es.kingcreek.swiftycompanion.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import es.kingcreek.swiftycompanion.R;
import es.kingcreek.swiftycompanion.constants.Constants;

public class LoginActivity extends AppCompatActivity {

    final String TAG = "LoginActivity";
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

            }
        }
    }
}