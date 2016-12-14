package com.styx.gta.donorblood.fragments.donorlist;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.adapters.DonorAdapter;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.ui.widget.FontTextView;

/**
 * Created by amal.george on 24-11-2016.
 */

public class DonorListFragment extends BaseFragment implements DonorListContract.View {
    public static final String TAG = "DonorListFragment";
    private DonorListContract.Presenter presenter;
    DonorAdapter adapter;
    BloodGroup thisGroup;
    FontTextView tv_title;
    LinearLayout ll_title;

    @Override
    protected void initUI() {
        setScreenTitle("Donor List");
        setScreenLayout(R.layout.fragment_donor_list);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new DonorListPresenter(this);
        adapter = new DonorAdapter(getContext());
        thisGroup = (BloodGroup) getArguments().getSerializable(Constants.FragmentParameters.keyObject);
        tv_title = ((FontTextView) rootView.findViewById(R.id.tv_title));
        ll_title = ((LinearLayout) rootView.findViewById(R.id.ll_title));
        RecyclerView mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.rv_donor_list));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        presenter.request();
        tv_title.setText(thisGroup.getName());
        ll_title.setBackgroundColor(Color.parseColor(thisGroup.getThemeColor()));

    }

    @Override
    public DonorAdapter getAdapter() {
        return adapter;
    }

    @Override
    public BloodGroup getThisGroup() {
        return thisGroup;
    }


}
