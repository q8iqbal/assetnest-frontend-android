package me.assetnest.assetnest_frontend_android.modul.scanasset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragment;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.modul.login.LoginActivity;
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

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void loadAssetByCode(String assetCode) {
        mPresenter.loadAssetByCode(assetCode);
    }

    private void initViews() {
        clFragmentScanAsset = fragmentView.findViewById((R.id.cl_fragment_scan_asset));
        etAssetCode = fragmentView.findViewById(R.id.et_asset_code);
        etAssetName = fragmentView.findViewById(R.id.et_asset_name);
        etCurrentStatus = fragmentView.findViewById(R.id.et_current_status);
        spinnerAction = fragmentView.findViewById(R.id.spinner_action);
        pbFragmentScanAsset = fragmentView.findViewById(R.id.pb_fragment_scan_asset);

        etAssetCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE
                        && !etAssetCode.getText().toString().equals("")) {
                    hideKeyboard(v);
                    loadAssetByCode(etAssetCode.getText().toString());
                    return true;
                }
                return false;
            }
        });

        clFragmentScanAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAssetCode.clearFocus();
                hideKeyboard(v);
            }
        });

        spinnerAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String newStatus = (String) parent.getItemAtPosition(position);
                String currentStatus = etCurrentStatus.getText().toString();

                if (currentAssetId != -1 && !newStatus.equalsIgnoreCase(currentStatus)) {
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
        int selectionIndex = getSpinnerStringPosition(spinnerAction, status);

        etAssetName.setText(name);
        etCurrentStatus.setText(status);
        if (selectionIndex != -1) {
            spinnerAction.setSelection(selectionIndex);
        }
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void resetFields() {
        setCurrentAssetId(-1);
        etAssetName.setText("");
        etCurrentStatus.setText("");
        spinnerAction.setSelection(getSpinnerStringPosition(spinnerAction, "Used"));
    }

    @Override
    public void redirectToLogin() {
        startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }

    private int getSpinnerStringPosition(Spinner spinner, String string) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(string)) {
                return i;
            }
        }

        return -1;
    }
}