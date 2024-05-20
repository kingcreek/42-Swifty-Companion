package es.kingcreek.swiftycompanion.api42.interfaces;

import java.util.List;

import es.kingcreek.swiftycompanion.api42.responses.AccessTokenResponse;
import es.kingcreek.swiftycompanion.api42.responses.CoalitionsResponse;
import es.kingcreek.swiftycompanion.api42.responses.UserInfoResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OAuthAPI {

    @FormUrlEncoded
    @POST("oauth/token")
    Call<AccessTokenResponse> getAccessToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code,
            @Field("redirect_uri") String redirectUri
    );

    @GET("v2/me")
    Call<UserInfoResponse> getUserInfo(@Header("Authorization") String authorization);

    @GET("/v2/coalitions")
    Call<List<CoalitionsResponse>> getCoalitions(
            @Header("Authorization") String authorization,
            @Query("user_id") String user_id
    );
}