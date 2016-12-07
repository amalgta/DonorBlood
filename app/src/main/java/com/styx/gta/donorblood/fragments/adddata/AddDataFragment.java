package com.styx.gta.donorblood.fragments.adddata;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.ui.widget.FontTextView;

/**
 * Created by amal.george on 24-11-2016.
 */

public class AddDataFragment extends BaseFragment implements AddDataContract.View {
    public static final String TAG = "AddDataFragment";
    private AddDataContract.Presenter presenter;
    @Override
    protected void initUI() {
        setScreenTitle("Add Data");
        setScreenLayout(R.layout.fragment_add_data);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
    }
}
