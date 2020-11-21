package me.assetnest.assetnest_frontend_android.modul.scanasset;

import android.view.MotionEvent;
import android.view.View;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class ScanAssetActivity extends BaseFragmentHolderActivity {
    ScanAssetFragment scanAssetFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

//         btBack.setVisibility(View.GONE);
//         btOptionMenu.setVisibility(View.GONE);
// //        ivIcon.setImageResource(R.drawable.....);
//         ivIcon.setVisibility(View.VISIBLE);

        scanAssetFragment = new ScanAssetFragment();
        setCurrentFragment(scanAssetFragment, false);
    }
}
