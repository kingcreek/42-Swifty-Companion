package es.kingcreek.swiftycompanion.api42;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import es.kingcreek.swiftycompanion.activities.WebViewActivity;
import es.kingcreek.swiftycompanion.api42.client.RetrofitClient;
import es.kingcreek.swiftycompanion.api42.interfaces.OAuthAPI;
import es.kingcreek.swiftycompanion.api42.interfaces.OnCoalitionListener;
import es.kingcreek.swiftycompanion.api42.interfaces.OnLoginListener;
import es.kingcreek.swiftycompanion.api42.interfaces.OnUserInfoListener;
import es.kingcreek.swiftycompanion.api42.responses.AccessTokenResponse;
import es.kingcreek.swiftycompanion.api42.responses.CoalitionsResponse;
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
                Log.e(TAG, "login response: " + response.code());
                if (response.isSuccessful()) {
                    AccessTokenResponse tokenResponse = response.body();
                    if (tokenResponse != null) {
                        String accessToken = tokenResponse.getAccessToken();
                        if (listener != null) {
                            listener.onLoginSuccess(accessToken);
                        }
                    } else {
                        if (listener != null) {
                            listener.onLoginFailure("Login failed with null response");
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onLoginFailure("Login failed with unsuccessful response");
                    }
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
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
                        if (listener != null) {
                            listener.onUserInfoSuccess(userInfoResponse);
                        }
                    } else {
                        if (listener != null) {
                            listener.onUserInfoFailure("getUserInfo failed with null response");
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onUserInfoFailure("getUserInfo failed with response code: " + response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                if (listener != null) {
                    listener.onUserInfoFailureNetwork("getUserInfo failed with network error: " + t.getMessage());
                }
            }
        });
    }

    public void getCoalitions(String accessToken, String user_id, final OnCoalitionListener listener) {
        Call<List<CoalitionsResponse>> call = oAuthAPI.getCoalitions("Bearer " + accessToken, user_id);

        call.enqueue(new Callback<List<CoalitionsResponse>>() {
            @Override
            public void onResponse(Call<List<CoalitionsResponse>> call, Response<List<CoalitionsResponse>> response) {
                if (response.isSuccessful()) {
                    List<CoalitionsResponse> coalitionsResponse = response.body();
                    if (coalitionsResponse != null) {
                        if (listener != null) {
                            listener.onCoalitionInfoSuccess(coalitionsResponse);
                        }
                    } else {
                        if (listener != null) {
                            listener.onCoalitionInfoFailure("getUserInfo failed with null response");
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onCoalitionInfoFailure("getUserInfo failed with response code: " + response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CoalitionsResponse>> call, Throwable t) {
                // Manejar error de red
                if (listener != null) {
                    listener.onCoalitionInfoFailure("getUserInfo failed with network error: " + t.getMessage());
                }
            }
        });
    }

    public void requestAutorizationCode(Activity activity)
    {
        Intent intent = new Intent(activity, WebViewActivity.class);
        activity.startActivityForResult(intent, Constants.WEBVIEW_RESULT);
    }

    public boolean haveAccessCode() {
        return  preferences.getAccessToken() != null;
    }

}
