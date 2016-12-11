package com.styx.gta.donorblood.fragments.adddata;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by amal.george on 24-11-2016.
 */

public class AddDataFragment extends BaseFragment implements AddDataContract.View {
    public static final String TAG = "AddDataFragment";
    private AddDataContract.Presenter presenter;
    private Spinner sp_blood_group;
    private Button bt_submit;
    private EditText et_name, et_number, et_address, et_dob;
    private RadioButton radio_male, radio_female;
    private ArrayAdapter<String> sp_blood_group_adapter;
    private HashMap<String, String> bloodGroupMap;

    @Override
    protected void initUI() {
        setScreenTitle("Add Data");
        setScreenLayout(R.layout.fragment_add_data);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        sp_blood_group = (Spinner) rootView.findViewById(R.id.sp_blood_group);
        bt_submit = (Button) rootView.findViewById(R.id.bt_submit);
        et_name = (EditText) rootView.findViewById(R.id.et_name);
        et_address = (EditText) rootView.findViewById(R.id.et_address);
        et_dob = (EditText) rootView.findViewById(R.id.et_dob);
        et_number = (EditText) rootView.findViewById(R.id.et_number);
        radio_female = (RadioButton) rootView.findViewById(R.id.radio_female);
        radio_male = (RadioButton) rootView.findViewById(R.id.radio_male);
        /** ET DOB */
        et_dob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence)){
                    et_dob.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        bloodGroupMap = new HashMap<>();
        presenter = new AddDataPresenter(this);
        sp_blood_group_adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item);
        sp_blood_group.setAdapter(sp_blood_group_adapter);
        initializeFields();

        presenter.requestBloodGroups();
        bt_submit.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             if (doValidate()) {
                                                 showCard(parseDonor());
                                             } else {
                                                 showError();
                                             }
                                         }
                                     }

        );
        et_dob.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          showDatePicker(view);
                                      }
                                  }
        );
    }

    private void initializeFields() {
        if (sp_blood_group_adapter.getCount() > 0)
            sp_blood_group.setSelection(0);
        radio_male.setChecked(true);
        et_name.setText("");
        et_dob.setText("");
        et_name.requestFocus();
        et_address.setText("");
        et_number.setText("");
    }

    @Override
    public void addGroupItem(String value, String name) {
        bloodGroupMap.put(value, name);
        sp_blood_group_adapter.add(name);
    }

    @Override
    public void bindDonorUI(View rootView, final Donor donor) {
        ((TextView) rootView.findViewById(R.id.tv_name)).setText(donor.getName());
        ((TextView) rootView.findViewById(R.id.tv_age)).setText(String.valueOf(Utilities.findAge(donor.getDob())));
        ((TextView) rootView.findViewById(R.id.tv_contact)).setText(donor.getContact());
        ((TextView) rootView.findViewById(R.id.tv_sex)).setText(donor.getSex());
        ((ImageView) rootView.findViewById(R.id.iv_user)).setImageResource(((donor.getSex().equalsIgnoreCase(Donor.Sex.male)) ? (R.drawable.ic_male) : (R.drawable.ic_female)));
        ((TextView) rootView.findViewById(R.id.tv_address)).setText(donor.getAddress());
        rootView.findViewById(R.id.iv_button_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?&q=" + donor.getAddress());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    try {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    } catch (ActivityNotFoundException innerEx) {
                        Toast.makeText(getContext(), "Please install a maps application", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onAddUserSuccess(boolean result) {
        getBase().hideProgressDialog();
        initializeFields();
        Toast.makeText(getActivity(), "User addition Success", Toast.LENGTH_SHORT).show();
    }

    void showDatePicker(final View view) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar m = Calendar.getInstance();
                m.set(i, i1, i2);
                ((EditText) view).setText(Constants.simpleDateFormat.format(m.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    void showCard(final Donor donor) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(donor.getName());

        final View rootView = LayoutInflater.from(getContext()).inflate(R.layout.item_donor_expanded, null);
        builder.setView(rootView);

        this.bindDonorUI(rootView, donor);

        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.saveDonor(donor);
                getBase().showProgressDialog();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    public boolean doValidate() {
        boolean flag = true;
        if (TextUtils.isEmpty(et_name.getText())) {
            et_name.setError("Required");
            flag = false;

        }
        if (TextUtils.isEmpty(et_number.getText())) {
            et_number.setError("Required");
            flag = false;
        }
        if (TextUtils.isEmpty(et_address.getText())) {
            et_address.setError("Required");
            flag = false;
        }
        if (TextUtils.isEmpty(et_dob.getText())) {
            et_dob.setError("Required");
            flag = false;
        }
        return flag;
    }

    Donor parseDonor() {
        Donor donor = new Donor();
        donor.setName(et_name.getText().toString());
        donor.setAddress(et_address.getText().toString());
        donor.setBloodGroup(sp_blood_group.getSelectedItem().toString());
        donor.setContact(et_number.getText().toString());
        donor.setDob(et_dob.getText().toString());
        if (radio_male.isChecked())
            donor.setSex(Donor.Sex.male);
        else
            donor.setSex(Donor.Sex.female);
        return donor;
    }

    void showError() {
        Toast.makeText(getContext(), "Please check all fields", Toast.LENGTH_SHORT).show();
    }
}
