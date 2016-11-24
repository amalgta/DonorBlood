package com.styx.gta.donorblood.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by amal.george on 24-11-2016.
 */

public class BaseFragment extends Fragment {
    String screenTitle;
    int screenLayout;
    protected View rootView;
    boolean isRoot;

    public BaseFragment() {
        isRoot = false;
        initUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(screenLayout, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getScreenTitle() != null) {
            getActivity().setTitle(screenTitle);
        }
    }

    protected void initUI() {
    }

    protected void setUI(Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            setUI(savedInstanceState);
        }
    }

    public String getScreenTitle() {
        return screenTitle;
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public void setScreenLayout(int screenLayout) {
        this.screenLayout = screenLayout;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }
}
