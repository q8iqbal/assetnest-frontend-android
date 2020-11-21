package me.assetnest.assetnest_frontend_android.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.model.Asset;

public class AssetAdapter extends ArrayAdapter<Asset> {
    public AssetAdapter(Context context, ArrayList<Asset> assets) {
        super(context, 0, assets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Asset assets = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.asset, parent, false);
        }
        // Lookup view for data population
        TextView tvAssetCode = (TextView) convertView.findViewById(R.id.tvAssetCode);
        TextView tvAssetName = (TextView) convertView.findViewById(R.id.tvAssetName);
        TextView tvAssectStatus = (TextView) convertView.findViewById(R.id.tvAssetStatus);
        // Populate the data into the template view using the data object
        tvAssetCode.setText("#"+assets.getCode());
        tvAssetName.setText(assets.getName());
        tvAssectStatus.setText(assets.getStatus());
        // Return the completed view to render on screen
        return convertView;
    }
}
