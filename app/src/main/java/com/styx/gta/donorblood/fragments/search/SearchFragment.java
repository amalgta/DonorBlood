package com.styx.gta.donorblood.fragments.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by amal.george on 24-11-2016.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View {
    public static final String TAG = "SearchFragment";
    private SearchContract.Presenter presenter;
    ArrayList<String> string;

    @Override
    protected void initUI() {
        setScreenTitle("Search");
        setScreenLayout(R.layout.fragment_search);

    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new SearchPresenter(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, string);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                rootView.findViewById(R.id.countries_list);
        textView.setAdapter(adapter);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.requestUserList("A");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public void getUserList(ArrayList<String> mList) {
        this.string = mList;
    }
}
