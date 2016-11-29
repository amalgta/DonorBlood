package com.styx.gta.donorblood.fragments.donordetail;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Utilities;

/**
 * Created by amal.george on 24-11-2016.
 */

public class DonorDetailFragment extends BaseFragment implements DonorDetailContract.View {
    public static final String TAG = "DonorDetailFragment";
    private DonorDetailContract.Presenter presenter;

    @Override
    protected void initUI() {
        setScreenTitle("DonorDetailFragment");
        setScreenLayout(R.layout.fragment_donor_detail);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new DonorDetailPresenter(this);
    }

    @Override
    public void bindDonorUI(final Donor donor) {
        ((TextView) rootView.findViewById(R.id.tv_name)).setText(donor.getName());
        ((TextView) rootView.findViewById(R.id.tv_age)).setText(String.valueOf(Utilities.getAge(donor.getDob())));
        ((TextView) rootView.findViewById(R.id.tv_contact)).setText(donor.getContact());
        ((TextView) rootView.findViewById(R.id.tv_sex)).setText(donor.getSex());
        if(donor.getSex().equalsIgnoreCase(Donor.Sex.female)){
            ((ImageView)rootView.findViewById(R.id.iv_user)).setImageResource(R.drawable.ic_female);
        }
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
    protected void doOnce() {
        presenter.request();
    }

    @Override
    public Bundle getViewArguments() {
        return getArguments();
    }

}
