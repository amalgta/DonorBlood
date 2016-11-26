package com.styx.gta.donorblood.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseActivity;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Logger;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by amal.george on 25-11-2016.
 */

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.ViewHolder>{
    private static String TAG = "DonorAdapter";
    private List<Donor> list;
    private Context mContext;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(Donor thisDonor);
    }

    public DonorAdapter(List<Donor> list, Context mContext, OnItemClickListener mListener) {
        this.list = list;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_donor, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Donor thisDonor = list.get(position);
        holder.bind(thisDonor, mListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_age, tv_sex, tv_contact, tv_blood_group;
        public ImageView iv_call_icon;

        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_age = (TextView) view.findViewById(R.id.tv_age);
            tv_sex = (TextView) view.findViewById(R.id.tv_sex);
            tv_contact = (TextView) view.findViewById(R.id.tv_contact);
            tv_blood_group = (TextView) view.findViewById(R.id.tv_blood_group);
            iv_call_icon = (ImageView) view.findViewById(R.id.iv_call_icon);
        }

        public void bind(final Donor thisDonor, final OnItemClickListener listener) {
            tv_name.setText(thisDonor.getName());
            try {
                Calendar mDob = Calendar.getInstance();
                mDob.setTime(Constants.simpleDateFormat.parse(thisDonor.getDob()));
                Calendar mToday = Calendar.getInstance();
                String mAge = String.valueOf(mToday.get(Calendar.YEAR) - mDob.get(Calendar.YEAR));
                tv_age.setText(mAge);
                iv_call_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((BaseActivity)mContext).call(thisDonor.getContact());
                    }
                });
            } catch (ParseException e) {
                tv_age.setVisibility(View.INVISIBLE);
                Logger.e(TAG, e.toString());
            }
            tv_sex.setText(thisDonor.getSex());
            tv_contact.setText(thisDonor.getContact());


            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            Query myTopPostsQuery2 = databaseReference.child("Data/BloodGroup/" + thisDonor.getBloodGroup());
            myTopPostsQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    BloodGroup thisGroup = dataSnapshot.getValue(BloodGroup.class);
                    //TODO Placeholders they say
                    tv_blood_group.setText(thisGroup.getName());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(thisDonor);
                }
            });

        }
    }
}
