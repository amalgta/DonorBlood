package com.styx.gta.donorblood.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.styx.gta.donorblood.utilities.Logger;

/**
 * Created by amal.george on 24-11-2016.
 */

public class BaseFragment extends Fragment {
    final String TAG = "BaseFragment";
    String screenTitle;
    int screenLayout;
    boolean alreadyLoaded = false;
    protected View rootView;
    boolean isRoot;

    public BaseFragment() {
        isRoot = false;
        initUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logger.e(TAG, "onCreateView");
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

    protected void doOnce() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e(TAG, "onCreate");

    }

    protected BaseActivity getBase() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Logger.e(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            setUI(savedInstanceState);
            if (!alreadyLoaded) {
                alreadyLoaded = true;
                doOnce();
            }
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
