package me.assetnest.assetnest_frontend_android.modul.asset;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class AssetActivity extends BaseFragmentHolderActivity {
    AssetFragment assetFragment;
    @Override
    protected void initializeFragment() {
        initializeView();
        assetFragment = new AssetFragment();
        setCurrentFragment(assetFragment, false);
    }
}
