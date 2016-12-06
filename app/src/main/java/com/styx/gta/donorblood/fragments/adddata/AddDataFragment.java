package com.styx.gta.donorblood.fragments.adddata;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.ui.widget.FontTextView;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by amal.george on 24-11-2016.
 */

public class AddDataFragment extends BaseFragment implements AddDataContract.View {
    public static final String TAG = "AddDataFragment";
    private AddDataContract.Presenter presenter;
    AlertDialog.Builder builder;
    View promptView;
    @Override
    protected void initUI() {
        setScreenTitle("Add Data");
        setScreenLayout(R.layout.fragment_add_data);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new AddDataPresenter(this);
        showDialogue();
    }

    void showDialogue() {
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Enter Access Code");
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        promptView = layoutInflater.inflate(R.layout.dialog_single_input, null);
        builder.setView(promptView);
        builder.setPositiveButton("ENTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.authenticateUser(((EditText)(promptView.findViewById(R.id.edittext))).getText().toString());
                showLoading();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getBase().onBackPressed();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    void showLoading(){
        rootView.findViewById(R.id.pg_loading).setVisibility(View.VISIBLE);
    }
    void stopLoading(){
        rootView.findViewById(R.id.pg_loading).setVisibility(View.GONE);
    }
    @Override
    public void showMD5(String text) {
        Log.e(TAG, text);
    }

    @Override
    public void authenticationResult(boolean access) {
        stopLoading();
        if(access){
            ((FontTextView)rootView.findViewById(R.id.tv_title)).setText("sucess");
        }else {
            ((FontTextView)rootView.findViewById(R.id.tv_title)).setText("fail");
        }
    }
}
