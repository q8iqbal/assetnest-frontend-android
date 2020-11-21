package me.assetnest.assetnest_frontend_android.modul.scanasset;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragment;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.utils.UtilProvider;

public class ScanAssetFragment
	extends BaseFragment<ScanAssetActivity, ScanAssetContract.Presenter>
	implements ScanAssetContract.View {
    ConstraintLayout clFragmentScanAsset;
    int currentAssetId;
    EditText etAssetCode;
    EditText etAssetName;
    EditText etCurrentStatus;
    Spinner spinnerAction;
    ProgressBar pbFragmentScanAsset;

    private void initViews() {
        clFragmentScanAsset = fragmentView.findViewById((R.id.cl_fragment_scan_asset));
        etAssetCode = fragmentView.findViewById(R.id.et_asset_code);
        etAssetName = fragmentView.findViewById(R.id.et_asset_name);
        etCurrentStatus = fragmentView.findViewById(R.id.et_current_status);
        spinnerAction = fragmentView.findViewById(R.id.spinner_action);
        pbFragmentScanAsset = fragmentView.findViewById(R.id.pb_fragment_scan_asset);

        etAssetCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("Assetnest-Android", "etAssetCode focus : " + hasFocus);
                if (!hasFocus && !etAssetCode.getText().toString().equals("")) {
                    String assetCode = etAssetCode.getText().toString();

                    mPresenter.loadAssetData(assetCode);
                }
            }
        });

        clFragmentScanAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Assetnest-Android", "ConstraintLayout clicked");
                etAssetCode.clearFocus();
            }
        });

        spinnerAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (currentAssetId != -1) {
                    String newStatus =
                            (String) ((ArrayAdapter) spinnerAction.getAdapter()).getItem(position);

                    mPresenter.saveStatusChange(currentAssetId, newStatus);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

	@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYXNzZXRuZXN0Lm1lXC9sb2dpbiIsImlhdCI6MTYwNTk1NzY4MSwiZXhwIjoxNjA1OTYxMjgxLCJuYmYiOjE2MDU5NTc2ODEsImp0aSI6IkFYakFrUkwwVXR2UjVxdlAiLCJzdWIiOjQsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.L3Wa3mdpRyDuu5E4s-2Sbi_XN2SicNOGAKHB44bspr0";

        UtilProvider.getSharedPreferenceUtil().setToken("Bearer " + token);
        fragmentView = inflater.inflate(R.layout.fragment_scan_asset, container, false);
        setPresenter(
            new ScanAssetPresenter(this, new ScanAssetInteractor(
                UtilProvider.getSharedPreferenceUtil()
            ))
        );
        mPresenter.start();
        initViews();
        setTitle("Scan Asset");

        return fragmentView;
    }

    @Override
    public void setPresenter(ScanAssetContract.Presenter presenter) {
    	mPresenter = presenter;
    }

    @Override
    public void setCurrentAssetId(int assetId) {
        currentAssetId = assetId;
    }

    @Override
    public void startLoading() {
        pbFragmentScanAsset.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        pbFragmentScanAsset.setVisibility(View.GONE);
    }

    @Override
    public void showAsset(Asset asset) {
        String name = asset.getName();
        String status = asset.getStatus();

        etAssetName.setText(name);
        etCurrentStatus.setText(status);
        spinnerAction.setSelection(((ArrayAdapter) spinnerAction.getAdapter()).getPosition(status));
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void redirectToLogin() {

    }
}