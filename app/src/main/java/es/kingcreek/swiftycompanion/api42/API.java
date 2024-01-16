package es.kingcreek.swiftycompanion.api42;

import android.content.Context;
import android.content.SharedPreferences;

import es.kingcreek.swiftycompanion.helper.PreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class API {

    private OAuthAPI oAuthAPI;
    private PreferencesManager preferences;


    public API(Context context) {
        // Initialize your Retrofit interface
        oAuthAPI = RetrofitClient.getClient().create(OAuthAPI.class);
        // Initialize preferences
        preferences = PreferencesManager.getInstance(context);
    }

    // Method to initiate login process
    public void login(String code, String redirectUri, final OnLoginListener listener) {
        Call<AccessTokenResponse> call = oAuthAPI.getAccessToken(
                "authorization_code",
                "tu_client_id",
                "tu_client_secret",
                code,
                redirectUri
        );

        call.enqueue(new Callback<AccessTokenResponse>() {
            @Override
            public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                if (response.isSuccessful()) {
                    AccessTokenResponse tokenResponse = response.body();
                    if (tokenResponse != null) {
                        String accessToken = tokenResponse.getAccessToken();

                        // Store the access token in SharedPreferences
                        preferences.saveAccessToken(accessToken);

                        // Notify the listener that the login was successful
                        if (listener != null) {
                            listener.onLoginSuccess(accessToken);
                        }
                    } else {
                        // Handle null response
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                // Handle network error
            }
        });
    }

    // Method to check if the user is logged in
    public boolean isLoggedIn() {
        // Check if the access token is stored in SharedPreferences
        return  preferences.getAccessToken() != null;
    }

    // Interface to listen for login events
    public interface OnLoginListener {
        void onLoginSuccess(String accessToken);

        // Add other methods as needed
    }
}
