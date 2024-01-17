package es.kingcreek.swiftycompanion.api42.interfaces;

import es.kingcreek.swiftycompanion.api42.responses.UserInfoResponse;

public interface OnUserInfoListener {

    void onUserInfoSuccess(UserInfoResponse userInfo);

    void onUserInfoFailure(String errorMessage);

}