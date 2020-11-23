package me.assetnest.assetnest_frontend_android.modul.detailasset;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class DetailAssetActivity  extends BaseFragmentHolderActivity {
    DetailAssetFragment detailAssetFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

//         btBack.setVisibility(View.GONE);
//         btOptionMenu.setVisibility(View.GONE);
// //      ivIcon.setImageResource(R.drawable.....);
//         ivIcon.setVisibility(View.VISIBLE);

        detailAssetFragment = new DetailAssetFragment();
        setCurrentFragment(detailAssetFragment, false);
    }
}
