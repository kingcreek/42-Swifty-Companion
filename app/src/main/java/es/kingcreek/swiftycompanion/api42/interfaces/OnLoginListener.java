package es.kingcreek.swiftycompanion.api42.interfaces;

// Interface to listen for login events
public interface OnLoginListener {
    void onLoginSuccess(String accessToken);
    void onLoginFailure(String message);

}