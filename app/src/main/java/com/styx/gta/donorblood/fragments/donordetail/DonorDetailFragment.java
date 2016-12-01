package com.styx.gta.donorblood.fragments.donordetail;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Utilities;

import java.util.Locale;

/**
 * Created by amal.george on 24-11-2016.
 */

public class DonorDetailFragment extends BaseFragment implements DonorDetailContract.View {
    public static final String TAG = "DonorDetailFragment";
    private DonorDetailContract.Presenter presenter;

    @Override
    protected void initUI() {
        setScreenTitle("Donor Details");
        setScreenLayout(R.layout.fragment_donor_detail);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new DonorDetailPresenter(this);
        presenter.request();

    }

    @Override
    public void bindDonorUI(final Donor donor) {
        ((TextView) rootView.findViewById(R.id.tv_name)).setText(donor.getName());
        ((TextView) rootView.findViewById(R.id.tv_age)).setText(String.valueOf(Utilities.getAge(donor.getDob())));
        ((TextView) rootView.findViewById(R.id.tv_contact)).setText(donor.getContact());
        ((TextView) rootView.findViewById(R.id.tv_sex)).setText(donor.getSex());
        ((ImageView) rootView.findViewById(R.id.iv_user)).setImageResource(((donor.getSex().equalsIgnoreCase(Donor.Sex.male)) ? (R.drawable.ic_male) : (R.drawable.ic_female)));
        ((TextView) rootView.findViewById(R.id.tv_address)).setText(donor.getAddress());
        rootView.findViewById(R.id.iv_button_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?&q="+donor.getAddress());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    try {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    } catch (ActivityNotFoundException innerEx) {
                        Toast.makeText(getContext(), "Please install a maps application", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

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
