package com.styx.gta.donorblood.fragments.donorlist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.adapters.DonorAdapter;
import com.styx.gta.donorblood.base.BaseFragment;

/**
 * Created by amal.george on 24-11-2016.
 */

public class DonorListFragment extends BaseFragment implements DonorListContract.View {
    public static final String TAG = "DonorListFragment";
    private DonorListContract.Presenter presenter;
    DonorAdapter adapter;

    @Override
    protected void initUI() {
        setScreenTitle("Donor List");
        setScreenLayout(R.layout.fragment_donor_list);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new DonorListPresenter(this);
        adapter = new DonorAdapter(getContext());
        RecyclerView mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.rv_donor_list));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        presenter.request();
    }
    @Override
    public DonorAdapter getAdapter() {
        return adapter;
    }

    @Override
    public Bundle getViewArguments() {
        return getArguments();
    }


}
