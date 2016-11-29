package com.styx.gta.donorblood.fragments.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.ui.widget.FontTextView;

/**
 * Created by amal.george on 24-11-2016.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    public static final String TAG = "HomeFragment";
    private HomeContract.Presenter presenter;
    BloodGroupAdapter adapter = new BloodGroupAdapter();

    @Override
    protected void initUI() {
        setRoot(true);
        setScreenTitle("HomeFragment");
        setScreenLayout(R.layout.fragment_home);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new HomePresenter(this);
        ((FontTextView) rootView.findViewById(R.id.tv_helloworld)).setText("0 Users Online");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.rv_bloodgroup));
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter.setContext(getContext());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void doOnce() {
        presenter.request();
    }

    @Override
    public HomeContract.BloodGroupAdapterView getAdapter() {
        return adapter;
    }
}
