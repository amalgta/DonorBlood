package com.styx.gta.donorblood.fragments.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.adapters.BloodGroupAdapter;
import com.styx.gta.donorblood.base.BaseFragment;

/**
 * Created by amal.george on 24-11-2016.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    public static final String TAG = "HomeFragment";
    private HomeContract.Presenter presenter;
    BloodGroupAdapter adapter;

    @Override
    protected void initUI() {
        setRoot(true);
        setScreenTitle("Donor Blood");
        setScreenLayout(R.layout.fragment_home);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new HomePresenter(this);
        adapter= new BloodGroupAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.rv_bloodgroup));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        presenter.requestTotalUserCount();
        presenter.request();

    }

    @Override
    public BloodGroupAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void setTotalUserCount(long totalUserCount) {
        String totalCountText;
        switch ((int) totalUserCount) {
            case 0:
                totalCountText = getString(R.string.text_zero_donor_ready);
                break;
            case 1:
                totalCountText = getString(R.string.text_one_donor_ready);
                break;
            default:
                totalCountText = getString(R.string.text_multi_donor_ready);
                break;
        }
        ((TextView) rootView.findViewById(R.id.tv_helloworld)).setText(totalUserCount+" "+totalCountText);
    }
}
