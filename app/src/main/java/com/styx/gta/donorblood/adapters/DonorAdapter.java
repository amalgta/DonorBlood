package com.styx.gta.donorblood.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseAdapter;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.constants.UserAction;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Utilities;

import java.util.ArrayList;

/**
 * Created by amal.george on 29-11-2016.
 */

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.ViewHolder> implements BaseAdapter<Donor> {
    private final String TAG = "DonorAdapter";
    private static final int layoutID = R.layout.item_donor_min;
    private ArrayList<Donor> list = new ArrayList<>();
    private Context context;

    public DonorAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void addItem(Donor item) {
        list.add(item);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(layoutID, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Donor thisDonor = list.get(position);
        holder.bind(thisDonor);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_age, tv_sex, tv_contact, tv_blood_group;
        public ImageView iv_call_icon, iv_user;

        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_age = (TextView) view.findViewById(R.id.tv_age);
            tv_sex = (TextView) view.findViewById(R.id.tv_sex);
            tv_contact = (TextView) view.findViewById(R.id.tv_contact);
            tv_blood_group = (TextView) view.findViewById(R.id.tv_blood_group);
            iv_call_icon = (ImageView) view.findViewById(R.id.iv_call_icon);
            iv_user = (ImageView) view.findViewById(R.id.iv_user);
        }

        public void bind(final Donor thisDonor) {
            tv_name.setText(thisDonor.getName());
            tv_age.setText(String.valueOf(Utilities.findAge(thisDonor.getDob())));
            iv_call_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilities.doCall(context, thisDonor.getContact());
                }
            });
            tv_sex.setText(thisDonor.getSex());
            iv_user.setImageResource(((thisDonor.getSex().equalsIgnoreCase(Donor.Sex.male)) ? (R.drawable.ic_male) : (R.drawable.ic_female)));
            tv_contact.setText(thisDonor.getContact());
            tv_blood_group.setText(thisDonor.getBloodGroup());
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(Constants.FragmentParameters.keyObject, thisDonor);
                    Utilities.getApp(context).doUserAction(UserAction.DONOR_DETAIL_FRAGMENT, mBundle);
                }
            });

        }
    }
}
