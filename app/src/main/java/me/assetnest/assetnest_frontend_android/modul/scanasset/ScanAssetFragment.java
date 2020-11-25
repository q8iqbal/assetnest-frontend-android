package me.assetnest.assetnest_frontend_android.modul.scanasset;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
    String currentAssetCode;
    EditText etAssetCode;
    EditText etAssetName;
    EditText etCurrentStatus;
    Spinner spinnerAction;
    Button buttonSaveChange;
    ProgressBar pbFragmentScanAsset;

    private void hideKeyboard(View view) {
        InputMethodManager imm
                = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
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
        buttonSaveChange = fragmentView.findViewById(R.id.button_save_change);
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

        clFragmentScanAsset.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideKeyboard(v);
            }
        });

        clFragmentScanAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAssetCode.clearFocus();
                hideKeyboard(v);
            }
        });

        buttonSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newStatus = spinnerAction.getSelectedItem().toString();
                String currentStatus = etCurrentStatus.getText().toString();

                if (currentAssetId != -1) {
                    if (newStatus.equalsIgnoreCase(currentStatus)) {
                        showToastMessage("Asset status already up-to-date.");
                    } else {
                        alertSaveChangeConfirmation();
                    }
                } else {
                    showToastMessage("No asset currently selected.");
                }
            }
        });
    }

    private void alertSaveChangeConfirmation() {
        String assetCode = currentAssetCode;
        String currentStatus = etCurrentStatus.getText().toString();
        final String newStatus = spinnerAction.getSelectedItem().toString();
        String alertMessage = "You're going to change asset \"" + assetCode + "\" status from \""
                + currentStatus + "\" to \"" + newStatus + "\". Are you sure?";
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Confirm Save Change")
                .setMessage(alertMessage)
                .setPositiveButton("Save Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.saveStatusChange(currentAssetId, newStatus);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

        alertDialog.show();
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
    public void setCurrentAssetCode(String code) {
        currentAssetCode = code;
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
        String code = asset.getCode();
        String name = asset.getName();
        String status = asset.getStatus();
        int selectionIndex = getSpinnerStringPosition(spinnerAction, status);

        etAssetCode.setText(code);
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
        setCurrentAssetCode(etAssetCode.getText().toString());
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