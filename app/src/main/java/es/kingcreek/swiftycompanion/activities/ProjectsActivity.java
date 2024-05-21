package es.kingcreek.swiftycompanion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.kingcreek.swiftycompanion.R;
import es.kingcreek.swiftycompanion.adapters.ProjectsAdapter;
import es.kingcreek.swiftycompanion.api42.responses.UserInfoResponse;

public class ProjectsActivity extends AppCompatActivity {

    final String TAG = "ProjectsActivity";
    RecyclerView recyclerview;
    private ProjectsAdapter projectsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        recyclerview = findViewById(R.id.recyclerview);
        Intent intent = getIntent();
        @SuppressWarnings("unchecked")
        ArrayList<UserInfoResponse.Projects> projects = (ArrayList<UserInfoResponse.Projects>) intent.getSerializableExtra("Projects");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        projectsAdapter = new ProjectsAdapter(this, projects);
        recyclerview.setAdapter(projectsAdapter);


    }
}
