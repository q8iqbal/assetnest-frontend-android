package me.assetnest.assetnest_frontend_android.modul.history;

import me.assetnest.assetnest_frontend_android.base.BaseFragmentHolderActivity;

public class HistoryActivity extends BaseFragmentHolderActivity {
    HistoryFragment historyFragment;
    @Override
    protected void initializeFragment() {
        initializeView();
        historyFragment = new HistoryFragment();
        setTitle("History");
        setCurrentFragment(historyFragment, false);
    }
}
