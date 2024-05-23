package es.kingcreek.swiftycompanion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import es.kingcreek.swiftycompanion.R;
import es.kingcreek.swiftycompanion.api42.API;
import es.kingcreek.swiftycompanion.api42.interfaces.OnCoalitionListener;
import es.kingcreek.swiftycompanion.api42.interfaces.OnLoginListener;
import es.kingcreek.swiftycompanion.api42.interfaces.OnUserInfoListener;
import es.kingcreek.swiftycompanion.api42.responses.CoalitionsResponse;
import es.kingcreek.swiftycompanion.api42.responses.UserInfoResponse;
import es.kingcreek.swiftycompanion.constants.Constants;
import es.kingcreek.swiftycompanion.helper.ColorUtils;
import es.kingcreek.swiftycompanion.helper.HelperFunctions;
import es.kingcreek.swiftycompanion.helper.PreferencesManager;
import es.kingcreek.swiftycompanion.views.CustomProgressBar;

public class MainActivity extends AppCompatActivity implements OnUserInfoListener, OnCoalitionListener {

    private final String TAG = "MainActivity";
    private API api;
    private PreferencesManager preferences;

    // Views
    ImageView profileImageView, coalitionImage;
    CustomProgressBar customProgressBar;
    TextView loginTextView, altariansTextView, pointsTextView, emailTextview, locationTextView;
    PieChart pieChart;
    Button seeProjectsButton, setExpiredToken;

    ArrayList<UserInfoResponse.Projects> projects = new ArrayList<>();

    // Parent Views
    AppBarLayout viewAppBar;
    NestedScrollView scrollView;
    ConstraintLayout constraintRefresh;
    Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = API.getInstance(this);
        preferences = PreferencesManager.getInstance(this);

        // Parent views
        viewAppBar        = findViewById(R.id.viewAppBar);
        scrollView        = findViewById(R.id.scrollView);
        refresh           = findViewById(R.id.refresh);
        constraintRefresh = findViewById(R.id.refreshView);

        constraintRefresh.setVisibility(View.GONE);
        viewAppBar.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);

        //views
        coalitionImage      = findViewById(R.id.coalitionImage);
        profileImageView    = findViewById(R.id.profileImageView);
        customProgressBar   = findViewById(R.id.customProgressBar);
        loginTextView       = findViewById(R.id.loginTextView);
        altariansTextView   = findViewById(R.id.altariansTextView);
        pointsTextView      = findViewById(R.id.pointsTextView);
        emailTextview       = findViewById(R.id.emailTextview);
        locationTextView    = findViewById(R.id.locationTextView);
        pieChart            = findViewById(R.id.pieChart);
        seeProjectsButton   = findViewById(R.id.seeProjectsButton);
        setExpiredToken     = findViewById(R.id.setExpiredToken);

        //default views
        coalitionImage.setVisibility(View.GONE);

        // Check if app have access token
        if(!api.haveAccessCode())
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            api.getUserInfo(preferences.getAccessToken(), this);
        }

        seeProjectsButton.setOnClickListener(v -> {
            if (projects.size() > 0) {
                Intent i = new Intent(this, ProjectsActivity.class);
                i.putExtra("Projects", projects);
                startActivity(i);
            }
        });

        setExpiredToken.setOnClickListener(view -> {
            // During evaluation can set this token to prove bonus part; refresh expired token
            preferences.setAccessToken("bc6c035becef935eaecdc03e63a1f3fa51dec3649b899351f89bfb42be2694b9");
            finish();
        });

        refresh.setOnClickListener(view -> {
            api.getUserInfo(preferences.getAccessToken(), this);
            constraintRefresh.setVisibility(View.GONE);
        });

    }

    @Override
    public void onUserInfoSuccess(UserInfoResponse userInfo) {
        populateUserData(userInfo);
    }

    @Override
    public void onUserInfoFailure(String errorMessage) {
        Log.e(TAG, "onUserInfoFailure:" + errorMessage);
        // Delete tokens
        preferences.setAccessToken(null);
        //preferences.setAutorizationCode(null);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.putExtra("newLogin", true);
        startActivity(intent);
        finish();
        //api.login(preferences.getAccessToken(), Constants.CLIENT_SECRET, null);
        //getUserInfo(accessToken, listener, retryCount - 1);
    }

    @Override
    public void onUserInfoFailureNetwork(String errorMessage) {
        Toast.makeText(getApplicationContext(), "Verify your internet connection.", Toast.LENGTH_LONG).show();
        constraintRefresh.setVisibility(View.VISIBLE);
        viewAppBar.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);
    }
    private void populateUserData(UserInfoResponse user)
    {
        constraintRefresh.setVisibility(View.GONE);
        viewAppBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.VISIBLE);

        // First of all, request coallitions
        api.getCoalitions(preferences.getAccessToken(), String.valueOf(user.getUserId()), this);

        // Load image
        Glide.with(this).load(user.getImage().getLink()).into(profileImageView);

        // get 42 cursus
        UserInfoResponse.CursusUser cursus = get42Cursus(user.getCursusUsers(), "42cursus");

        //Progress bar
        customProgressBar.setCenterText(HelperFunctions.getLevelFormat(cursus.getLevel()));
        customProgressBar.setProgress(HelperFunctions.getLvlPercent(cursus.getLevel()));
        customProgressBar.setBarColor(getColor(R.color.tiamant));

        //chart
        List<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < cursus.getSkills().size(); i++) {
            entries.add(new PieEntry((float) cursus.getSkills().get(i).getLevel(), cursus.getSkills().get(i).getName()));
        }
        PieDataSet dataSet = new PieDataSet(entries, "Skills");

        List<Integer> colors = ColorUtils.generateColorPalette(entries.size(), 0, 360, 0.8f, 0.6f);

        dataSet.setColors(colors);
        //dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData pieData = new PieData(dataSet);
        pieData.setValueTextColor(Color.BLACK);
        pieData.setValueTextSize(14f);

        pieChart.setData(pieData);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);



        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        pieChart.invalidate();

        // Cardview info
        loginTextView.setText(user.getLogin());
        altariansTextView.setText(user.getWallet() + " ₳");
        pointsTextView.setText(user.getCorrectionPoint() + " eval. points");
        emailTextview.setText(user.getEmail());
        if(user.getLocation() == null){
            locationTextView.setText("No Location");
        } else {
            locationTextView.setText(user.getLocation());
        }
        emailTextview.setSelected(true);

        projects.clear();
        projects.addAll(user.getProjectsUsers());

    }

    private UserInfoResponse.CursusUser get42Cursus(List<UserInfoResponse.CursusUser> cursus, String search)
    {
        for (UserInfoResponse.CursusUser c : cursus)
        {
            if (c.getCursus().getName().equals(search))
                return c;
        }
        return null;
    }
    @Override
    public void onCoalitionInfoSuccess(List<CoalitionsResponse> coalitionsInfo) {
        // Coalition is always 0
        CoalitionsResponse c = coalitionsInfo.get(0);
        int color = 0;
        Drawable drawable = null;
        switch (c.getName().toLowerCase())
        {
            case "tiamant":
                color       = getResources().getColor(R.color.tiamant);
                drawable    = getResources().getDrawable(R.drawable.tiamant);
                setTheme(R.style.Theme_SwiftyCompanion_tiamant);
                break;
            case "marventis":
                color       = getResources().getColor(R.color.marventis);
                drawable    = getResources().getDrawable(R.drawable.marventis);
                setTheme(R.style.Theme_SwiftyCompanion_marventis);
                break;
            case "ignisaria":
                color       = getResources().getColor(R.color.ignisaria);
                drawable    = getResources().getDrawable(R.drawable.ignisaria);
                setTheme(R.style.Theme_SwiftyCompanion_ignisaria);
                break;
            case "zefiria":
                color       = getResources().getColor(R.color.zefiria);
                drawable    = getResources().getDrawable(R.drawable.zefiria);
                setTheme(R.style.Theme_SwiftyCompanion_zefiria);
                break;
        }
        changeCoalition(color, drawable);
    }

    void changeCoalition(int color, Drawable drawable)
    {
        coalitionImage.setVisibility(View.VISIBLE);
        VectorDrawableCompat vectorDrawable = VectorDrawableCompat.create(getResources(), R.drawable.coalition_shape, getTheme());
        vectorDrawable.setTint(color);
        coalitionImage.setBackground(vectorDrawable);
        coalitionImage.setImageDrawable(drawable);
    }

    @Override
    public void onCoalitionInfoFailure(String errorMessage) {
        Log.e(TAG, "onCoalitionInfoFailure:" + errorMessage);
    }
}