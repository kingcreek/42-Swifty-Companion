package es.kingcreek.swiftycompanion.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.kingcreek.swiftycompanion.R;
import es.kingcreek.swiftycompanion.api42.responses.UserInfoResponse;


public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder> {

    private ArrayList<UserInfoResponse.Projects> projects;
    private Context context;

    public ProjectsAdapter(Context context, ArrayList<UserInfoResponse.Projects> projects) {
        this.context = context;
        this.projects = projects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.projects_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserInfoResponse.Projects project = projects.get(position);

        holder.projectName.setText(project.getProject().getName());
        holder.projectStatus.setText(project.getStatus());
        holder.projectGrade.setText(project.getFinal_mark());
        /*
        holder.projectName.setText(project.);
        holder.textViewMessage.setText(smsModel.getMessage());
        if (smsModel.getInOut() == 1)
            holder.imageViewMessageType.setImageDrawable(context.getResources().getDrawable(R.drawable.arrowdown));
        else
            holder.imageViewMessageType.setImageDrawable(context.getResources().getDrawable(R.drawable.arrowup));
         */

    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView projectName, projectStatus, projectGrade;
        public ViewHolder(View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectName);
            projectStatus = itemView.findViewById(R.id.projectStatus);
            projectGrade = itemView.findViewById(R.id.projectGrade);
        }
    }
}