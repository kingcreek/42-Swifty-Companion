package es.kingcreek.swiftycompanion.api42.interfaces;


import java.util.List;

import es.kingcreek.swiftycompanion.api42.responses.CoalitionsResponse;

public interface OnCoalitionListener {
    void onCoalitionInfoSuccess(List<CoalitionsResponse> coalitionsInfo);
    void onCoalitionInfoFailure(String errorMessage);
}