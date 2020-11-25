package me.assetnest.assetnest_frontend_android.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.modul.history.HistoryActivity;
import me.assetnest.assetnest_frontend_android.modul.home.HomeActivity;
import me.assetnest.assetnest_frontend_android.modul.profile.ProfileActivity;
import me.assetnest.assetnest_frontend_android.modul.scanasset.ScanAssetActivity;


public abstract class BaseFragment<T extends FragmentActivity, U extends BasePresenter> extends Fragment{

    protected String title;
    protected T activity;
    protected View fragmentView;
    protected U mPresenter;
    protected FragmentListener fragmentListener;

    protected void setTitle(String title) {
        this.title = title;
        fragmentListener.setTitle(title);
    }

    protected String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        title = getResources().getString(R.string.app_name);
        setTitle(title);
        return view;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (T) context;
        this.fragmentListener = (FragmentListener) context;
    }


}
