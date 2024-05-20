package es.kingcreek.swiftycompanion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.kingcreek.swiftycompanion.R;

public class ProjectsActivity extends AppCompatActivity {

    final String TAG = "ProjectsActivity";
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        recyclerview = findViewById(R.id.recyclerview);
        Intent intent = getIntent();
        @SuppressWarnings("unchecked")
        ArrayList<Object> projects = (ArrayList<Object>) intent.getSerializableExtra("Projects");
        /*
        for(int i = 0; i < projects.size(); i++) {
            Log.e(TAG, ": " + projects.get(i));
        }
         */


    }
}
