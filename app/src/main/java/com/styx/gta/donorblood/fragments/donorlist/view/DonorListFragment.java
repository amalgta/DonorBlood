package com.styx.gta.donorblood.fragments.donorlist.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.ui.widget.FontTextView;

/**
 * Created by amal.george on 24-11-2016.
 */

public class DonorListFragment extends BaseFragment {
    public static final String TAG = "DonorListFragment";

    @Override
    protected void initUI() {
        setScreenTitle("DonorListFragment");
        setScreenLayout(R.layout.fragment_donor_list);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        RecyclerView mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.rv_donor_list));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        BloodGroup bloodGroup = (BloodGroup) getArguments().getSerializable(Constants.FragmentParameters.keyObject);
        DonorAdapterImpl adapter = new DonorAdapterImpl(getContext(), bloodGroup);
        adapter.request();
        mRecyclerView.setAdapter(adapter);
        ((FontTextView) rootView.findViewById(R.id.tv_title)).setText(bloodGroup.getName());
    }
}
