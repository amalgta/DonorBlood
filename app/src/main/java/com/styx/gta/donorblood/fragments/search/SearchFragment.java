package com.styx.gta.donorblood.fragments.search;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.adapters.DonorAdapter;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Utilities;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by amal.george on 24-11-2016.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View {
    public static final String TAG = "SearchFragment";
    private SearchContract.Presenter presenter;
    private EditText et_min_age, et_max_age, et_address;
    private Spinner sp_blood_group;
    private CheckBox cb_male, cb_female;
    private Button bt_search;
    private HashMap<String, String> bloodGroupMap;
    private ArrayAdapter<String> sp_blood_group_adapter;
    DonorAdapter adapter;

    @Override
    protected void initUI() {
        setScreenTitle("Search");
        setScreenLayout(R.layout.fragment_search);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new SearchPresenter(this);
        bloodGroupMap = new HashMap<>();
        sp_blood_group_adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item);
        et_min_age = (EditText) rootView.findViewById(R.id.et_min_age);
        et_max_age = (EditText) rootView.findViewById(R.id.et_max_age);
        et_address = (EditText) rootView.findViewById(R.id.et_address);
        sp_blood_group = (Spinner) rootView.findViewById(R.id.sp_blood_group);
        sp_blood_group.setAdapter(sp_blood_group_adapter);
        cb_male = (CheckBox) rootView.findViewById(R.id.cb_male);
        cb_female = (CheckBox) rootView.findViewById(R.id.cb_female);
        bt_search = (Button) rootView.findViewById(R.id.bt_search);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                getBase().showProgressDialog();
                presenter.requestMatches(et_address.getText().toString(), sp_blood_group.getSelectedItem().toString(), (cb_male.isChecked() ? (cb_female.isChecked() ? "both" : Donor.Sex.male) : (cb_female.isChecked() ? Donor.Sex.female : "both")), et_min_age.getText().toString(), et_max_age.getText().toString());
            }
        });
        presenter.requestBloodGroups();
        addGroupItem("ALL","ALL");
        adapter = new DonorAdapter(getContext());

        RecyclerView mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.rv_donor_list));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void addGroupItem(String value, String name) {
        bloodGroupMap.put(value, name);
        sp_blood_group_adapter.add(name);
    }

    @Override
    public void addSearchResult(Donor donor) {
        adapter.addItem(donor);
    }

    @Override
    public void onDataReceive() {
        getBase().hideProgressDialog();
    }
}
