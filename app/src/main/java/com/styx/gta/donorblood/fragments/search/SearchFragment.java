package com.styx.gta.donorblood.fragments.search;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;

/**
 * Created by amal.george on 24-11-2016.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View {
    public static final String TAG = "SearchFragment";
    private SearchContract.Presenter presenter;
    Spinner sp_blood_group;
    ArrayAdapter bloodGroupAdapter, nameAdapter;

    @Override
    protected void initUI() {
        setScreenTitle("Search");
        setScreenLayout(R.layout.fragment_search);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new SearchPresenter(this);
        bloodGroupAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_blood_group = (Spinner) rootView.findViewById(R.id.sp_blood_group);
        sp_blood_group.setAdapter(bloodGroupAdapter);
        new AlertDialog.Builder(getContext()).setTitle("Search Not Implemented").setMessage("I am restricted by the technology of this time, but I will implement search one day").setCancelable(true).show();

    }

    @Override
    protected void doOnce() {
        presenter.request();
    }

    @Override
    public ArrayAdapter<String> getBloodGroupAdapter() {
        return bloodGroupAdapter;
    }
}
