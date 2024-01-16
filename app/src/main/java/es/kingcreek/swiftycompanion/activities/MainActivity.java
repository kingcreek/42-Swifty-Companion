package es.kingcreek.swiftycompanion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import es.kingcreek.swiftycompanion.R;
import es.kingcreek.swiftycompanion.api42.API;

public class MainActivity extends AppCompatActivity {

    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = new API(this);
        if (!api.isLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}