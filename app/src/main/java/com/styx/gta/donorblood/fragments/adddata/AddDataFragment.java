package com.styx.gta.donorblood.fragments.adddata;

import android.app.DatePickerDialog;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.ui.widget.FontTextView;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by amal.george on 24-11-2016.
 */

public class AddDataFragment extends BaseFragment implements AddDataContract.View {
    public static final String TAG = "AddDataFragment";
    private AddDataContract.Presenter presenter;
    private Spinner spinner;
    private Button bt_dob,bt_submit;
    private ArrayAdapter<String> adapter;
    private HashMap<String, String> bloodGroupMap;

    @Override
    protected void initUI() {
        setScreenTitle("Add Data");
        setScreenLayout(R.layout.fragment_add_data);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new AddDataPresenter(this);
        presenter.requestBloodGroups();
        spinner = (Spinner) rootView.findViewById(R.id.sp_blood_group);
        bt_dob = (Button) rootView.findViewById(R.id.bt_dob);
        bt_submit = (Button) rootView.findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mc=(Calendar)bt_dob.getTag();
                Toast.makeText(getContext(), mc.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        bt_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(view);
            }
        });
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item);
        bloodGroupMap = new HashMap<>();
        spinner.setAdapter(adapter);
    }

    @Override
    public void addGroupItem(String value, String name) {
        bloodGroupMap.put(value, name);
        adapter.add(name);
    }

    void showDatePicker(final View view) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar m = Calendar.getInstance();
                m.set(i, i1, i2);
                view.setTag(m);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
