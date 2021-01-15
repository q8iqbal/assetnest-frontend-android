package me.assetnest.assetnest_frontend_android.base;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.assetnest.assetnest_frontend_android.R;

public abstract class BaseFragmentHolderActivity extends BaseActivity{

    protected TextView tvToolbarTitle;
    protected FrameLayout flFragmentContainer;
    protected RelativeLayout rlActivityFragmentHolder;

    @Override
    protected void initializeFragment() {

    }

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_activity);
        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        flFragmentContainer = (FrameLayout) findViewById(R.id.flFragmentContainer);
        rlActivityFragmentHolder = (RelativeLayout) findViewById(R.id.rlActivityFragmentHolder);
    }

    public void setTitle(String title) {
        this.tvToolbarTitle.setText(title);
    }

}
