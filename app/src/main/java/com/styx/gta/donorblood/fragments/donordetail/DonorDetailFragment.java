package com.styx.gta.donorblood.fragments.donordetail;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.models.Donor;

/**
 * Created by amal.george on 24-11-2016.
 */

public class DonorDetailFragment extends BaseFragment implements DonorDetailContract.View {
    public static final String TAG = "DonorDetailFragment";
    private DonorDetailContract.Presenter mPresenter;

    @Override
    protected void initUI() {
        setScreenTitle("DonorDetailFragment");
        setScreenLayout(R.layout.fragment_donor_detail);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        mPresenter = new DonorDetailPresenter(this);
    }

    @Override
    public void bindDonorUI(final Donor donor) {
        ((TextView) rootView.findViewById(R.id.tv_name)).setText(donor.getName());
        rootView.findViewById(R.id.tv_share_to_whatsapp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.putExtra(Intent.EXTRA_TEXT, donor.getName() + " " + donor.getContact());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    @Override
    public Bundle getViewArguments() {
        return getArguments();
    }

}
