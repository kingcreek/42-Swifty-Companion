package es.kingcreek.swiftycompanion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import es.kingcreek.swiftycompanion.R;
import es.kingcreek.swiftycompanion.constants.Constants;

public class WebViewActivity extends AppCompatActivity {

    final String TAG = "WebViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());

        String AUTHORIZATION_URL = Constants.BASE_URL;
        AUTHORIZATION_URL += "/oauth/authorize?client_id=" + Constants.CLIENT_ID;
        AUTHORIZATION_URL += "&redirect_uri=" + Constants.CALLBACK_URL;
        AUTHORIZATION_URL += "&response_type=code";

        webView.loadUrl(AUTHORIZATION_URL);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Check if the URL contains the redirect URI
            if (url.startsWith(Constants.CALLBACK_URL)) {
                // Extract the authorization code from the URL
                String code = getCodeFromUrl(url);

                // Create an intent to pass the authorization code back to the LoginActivity
                Intent intent = new Intent();
                intent.putExtra("authorization_code", code);

                // Set the result and finish the activity
                setResult(RESULT_OK, intent);
                finish();
            }

            // Load the URL in the WebView
            view.loadUrl(url);
            return true;
        }
    }

    // Method to extract the authorization code from the URL
    private String getCodeFromUrl(String url) {
        // Logic to extract the code from the URL (implement according to the actual response format)
        // For simplicity, you may use a URL parsing library or regular expressions
        // Example: If the URL is like "swiftycompanionapp://oauth2callback/oauth2callback?code=AUTHORIZATION_CODE"
        String[] parts = url.split("=");
        if (parts.length > 1) {
            return parts[1];
        } else {
            return null;
        }
    }
}
