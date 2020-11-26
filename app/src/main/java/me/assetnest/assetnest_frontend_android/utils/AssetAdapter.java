package me.assetnest.assetnest_frontend_android.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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
        final Asset asset = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.asset, parent, false);
        }
        // Lookup view for data population
        TextView tvAssetCode = (TextView) convertView.findViewById(R.id.tvAssetCode);
        TextView tvAssetName = (TextView) convertView.findViewById(R.id.tvAssetName);
        TextView tvAssectStatus = (TextView) convertView.findViewById(R.id.tv_asset_status);
        ImageView ivAsset = (ImageView) convertView.findViewById(R.id.iv_asset);
        // Populate the data into the template view using the data object
        tvAssetCode.setText("#"+asset.getCode());
        tvAssetName.setText(asset.getName());
        tvAssectStatus.setText(asset.getStatus());
        tampilanLog(asset);
        if(asset.getImage()!=null)
            Glide.with(convertView).load(Constant.BASE_URL+asset.getImage()).into(ivAsset);
        // Return the completed view to render on screen
        return convertView;
    }

    public void tampilanLog(Asset asset){
        String TAG = "LIAT ISI ASSET";
        Log.d(TAG, "namane = "+asset.getName()+", img nya = "+asset.getImage());
    }

}
