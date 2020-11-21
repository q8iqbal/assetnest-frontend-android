package me.assetnest.assetnest_frontend_android.modul.detailasset;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import me.assetnest.assetnest_frontend_android.R;
import me.assetnest.assetnest_frontend_android.base.BaseFragment;
import me.assetnest.assetnest_frontend_android.model.Asset;
import me.assetnest.assetnest_frontend_android.utils.UtilProvider;

public class DetailAssetFragment
        extends BaseFragment<DetailAssetActivity, DetailAssetContract.Presenter>
        implements DetailAssetContract.View {
    ProgressBar pbDetailAsset;
    TextView tvAssetIdValue;
    TextView tvNameValue;
    TextView tvTypeValue;
    TextView tvStatusValue;
    TextView tvPuchaseDateValue;
    TextView tvLocationValue;
    TextView tvPriceValue;
    TextView tvAttachmentValue;
    TextView tvNoteValue;


    private void initViews(){
        pbDetailAsset = fragmentView.findViewById(R.id.pbDetailAsset);
        tvAssetIdValue =  fragmentView.findViewById(R.id.tv_asset_id_value);
        tvNameValue =  fragmentView.findViewById(R.id.tv_name_value);
        tvTypeValue =  fragmentView.findViewById(R.id.tv_type_value);
        tvStatusValue =  fragmentView.findViewById(R.id.tv_status_value);
        tvPuchaseDateValue =  fragmentView.findViewById(R.id.tv_puchase_date_value);
        tvLocationValue =  fragmentView.findViewById(R.id.tv_location_value);
        tvPriceValue =  fragmentView.findViewById(R.id.tv_price_value);
        tvAttachmentValue =  fragmentView.findViewById(R.id.tv_attachment_value);
        tvNoteValue =  fragmentView.findViewById(R.id.tv_note_value);
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
                new DetailAssetPresenter(this, new DetailAssetInteractor(
                        UtilProvider.getSharedPreferenceUtil()
                ))
        );
        mPresenter.start();
        initViews();
        setTitle("Detail Asset");

        return fragmentView;
    }

    @Override
    public void startLoading() {
        pbDetailAsset.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        pbDetailAsset.setVisibility(View.GONE);
    }

    @Override
    public void showAsset(Asset asset){
        tvAssetIdValue.setText(asset.getCode());
        tvNameValue.setText(asset.getName());
        tvTypeValue.setText(asset.getType());
        tvStatusValue.setText(asset.getStatus());
        tvPuchaseDateValue.setText(asset.getPurchaseDate());
        tvLocationValue.setText(asset.getLocation());
        tvPriceValue.setText(asset.getPrice());
        tvNoteValue.setText(asset.getNote());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void redirectToLogin() {

    }

    @Override
    public void setPresenter(DetailAssetPresenter presenter) {
        mPresenter = presenter;
    }
}
