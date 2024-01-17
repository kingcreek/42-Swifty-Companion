package es.kingcreek.swiftycompanion.api42;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import es.kingcreek.swiftycompanion.api42.client.RetrofitClient;
import es.kingcreek.swiftycompanion.api42.interfaces.OAuthAPI;
import es.kingcreek.swiftycompanion.api42.interfaces.OnLoginListener;
import es.kingcreek.swiftycompanion.api42.interfaces.OnUserInfoListener;
import es.kingcreek.swiftycompanion.api42.responses.AccessTokenResponse;
import es.kingcreek.swiftycompanion.api42.responses.UserInfoResponse;
import es.kingcreek.swiftycompanion.constants.Constants;
import es.kingcreek.swiftycompanion.helper.PreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class API {

    private final String TAG = "API";
    private static API instance;
    private OAuthAPI oAuthAPI;
    private PreferencesManager preferences;


    public API(Context context) {
        // Initialize your Retrofit interface
        oAuthAPI = RetrofitClient.getClient().create(OAuthAPI.class);
        // Initialize preferences
        preferences = PreferencesManager.getInstance(context);
    }

    // Singleton
    public static synchronized API getInstance(Context context) {
        if (instance == null) {
            instance = new API(context);
        }
        return instance;
    }

    // Method to initiate login process
    public void login(String code, String secret, final OnLoginListener listener) {
        Call<AccessTokenResponse> call = oAuthAPI.getAccessToken(
                "authorization_code",
                Constants.CLIENT_ID,
                secret,
                code,
                Constants.CALLBACK_URL
        );

        call.enqueue(new Callback<AccessTokenResponse>() {
            @Override
            public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                if (response.isSuccessful()) {
                    AccessTokenResponse tokenResponse = response.body();
                    if (tokenResponse != null) {
                        String accessToken = tokenResponse.getAccessToken();
                        // Notify the listener that the login was successful
                        if (listener != null) {
                            listener.onLoginSuccess(accessToken);
                        }
                    } else {
                        // Handle null response
                        if (listener != null) {
                            listener.onLoginFailure("Login failed with null response");
                        }
                    }
                } else {
                    // Handle unsuccessful response
                    if (listener != null) {
                        listener.onLoginFailure("Login failed with unsuccessful response");
                    }
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                // Handle network error
                if (listener != null) {
                    listener.onLoginFailure("Login failed with network error: " + t.getMessage());
                }
            }
        });
    }

    public void getUserInfo(String accessToken, final OnUserInfoListener listener) {
        Call<UserInfoResponse> call = oAuthAPI.getUserInfo("Bearer " + accessToken);

        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if (response.isSuccessful()) {
                    UserInfoResponse userInfoResponse = response.body();
                    if (userInfoResponse != null) {
                        // Notificar al listener que la obtención de información fue exitosa
                        if (listener != null) {
                            listener.onUserInfoSuccess(userInfoResponse);
                        }
                    } else {
                        // Manejar respuesta nula
                        if (listener != null) {
                            listener.onUserInfoFailure("getUserInfo failed with null response");
                        }
                    }
                } else {
                    // Manejar respuesta no exitosa
                    if (listener != null) {
                        listener.onUserInfoFailure("getUserInfo failed with response code: " + response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                // Manejar error de red
                if (listener != null) {
                    listener.onUserInfoFailure("getUserInfo failed with network error: " + t.getMessage());
                }
            }
        });
    }

    // Method to check if the app have access token
    public boolean haveAutorizationCode() {
        // Check if the access token is stored in SharedPreferences
        return  preferences.getAutorizationCode() != null;
    }

}
