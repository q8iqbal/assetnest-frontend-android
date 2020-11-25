package me.assetnest.assetnest_frontend_android.modul.scanasset;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class ScanAssetActivity extends BaseFragmentHolderActivity {
    ScanAssetFragment scanAssetFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        scanAssetFragment = new ScanAssetFragment();
        setCurrentFragment(scanAssetFragment, false);
    }
}
