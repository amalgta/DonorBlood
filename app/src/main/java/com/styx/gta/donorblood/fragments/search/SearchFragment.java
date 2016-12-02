package com.styx.gta.donorblood.fragments.search;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;

/**
 * Created by amal.george on 24-11-2016.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View {
    public static final String TAG = "SearchFragment";
    Spinner sp_blood_group;
    ArrayAdapter<String> bloodGroupAdapter;
    EditText et_min_age, et_max_age, et_address;
    SearchContract.Presenter presenter;

    @Override
    protected void initUI() {
        setScreenTitle("Search");
        setScreenLayout(R.layout.fragment_search);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        bloodGroupAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item);
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_min_age = (EditText) rootView.findViewById(R.id.et_min_age);
        et_max_age = (EditText) rootView.findViewById(R.id.et_max_age);
        et_address = (EditText) rootView.findViewById(R.id.et_address);
        sp_blood_group = (Spinner) rootView.findViewById(R.id.sp_blood_group);
        sp_blood_group = (Spinner) rootView.findViewById(R.id.sp_blood_group);
        sp_blood_group.setAdapter(bloodGroupAdapter);
        presenter = new SearchPresenter(this);
        presenter.request();
        Button bt_search=(Button)rootView.findViewById(R.id.bt_search);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.searchDonor(sp_blood_group.getSelectedItem().toString(),Integer.parseInt(et_min_age.getText().toString()),Integer.parseInt(et_max_age.getText().toString()),et_address.getText().toString());
            }
        });
    }

    @Override
    public void showResult(String result) {
        Toast.makeText(getBase(), result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public ArrayAdapter<String> getBloodGroupAdapter() {
        return bloodGroupAdapter;
    }

}
