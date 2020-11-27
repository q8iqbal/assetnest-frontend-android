package me.assetnest.assetnest_frontend_android.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.model.History;

public class HistoryAdapter extends ArrayAdapter<History> {
    public HistoryAdapter(Context context, ArrayList<History> histories) {
        super(context, 0, histories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        History history = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.history, parent, false);
        }
        // Lookup view for data population
        TextView tvHistoryName = (TextView) convertView.findViewById(R.id.tv_history_name);
        TextView tvHistoryStatus = (TextView) convertView.findViewById(R.id.tv_asset_status);
        TextView tvHistoryDate = (TextView) convertView.findViewById(R.id.tv_history_date);
        // Populate the data into the template view using the data object
        if(history.getStatus().equals("Idle"))
            tvHistoryStatus.setBackgroundResource(R.drawable.rc_status_idle);
        else if(history.getStatus().equals("Used"))
            tvHistoryStatus.setBackgroundResource(R.drawable.rc_status_used);
        else if(history.getStatus().equals("Maintenance"))
            tvHistoryStatus.setBackgroundResource(R.drawable.rc_status_maintenance);
        else if(history.getStatus().equals("Lost"))
            tvHistoryStatus.setBackgroundResource(R.drawable.rc_status_lost);
        else if(history.getStatus().equals("Broken"))
            tvHistoryStatus.setBackgroundResource(R.drawable.rc_status_broken);
        tvHistoryName.setText(history.getName());
        tvHistoryStatus.setText(history.getStatus());
        tvHistoryDate.setText(history.getDate());
        // Return the completed view to render on screen
        return convertView;
    }
}
