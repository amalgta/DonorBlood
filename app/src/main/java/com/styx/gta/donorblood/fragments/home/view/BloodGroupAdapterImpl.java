package com.styx.gta.donorblood.fragments.home.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.constants.UserAction;
import com.styx.gta.donorblood.fragments.donorlist.presenter.DonorPresenterImpl;
import com.styx.gta.donorblood.fragments.home.presenter.BloodGroupPresenterImpl;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amal.george on 25-11-2016.
 */

public class BloodGroupAdapterImpl extends RecyclerView.Adapter<BloodGroupAdapterImpl.ViewHolder> implements BloodGroupAdapter {


    private static String TAG = "DonorAdapterImpl";
    private final ArrayList<BloodGroup> list = new ArrayList<>();
    private final BloodGroupPresenterImpl presenter;
    private Context context;

    public BloodGroupAdapterImpl(Context context) {
        this.presenter = new BloodGroupPresenterImpl(this);
        this.context = context;
    }

    @Override
    public void addItem(BloodGroup bloodGroup) {
        list.add(bloodGroup);
        notifyDataSetChanged();
    }

    @Override
    public void request() {
        presenter.request();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_blood_group, parent, false);

        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BloodGroup thisBloodGroup = list.get(position);
        holder.bind(thisBloodGroup);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_total_count, tv_total_count_text;
        CardView ll_background;

        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_total_count = (TextView) view.findViewById(R.id.tv_total_count);
            ll_background = (CardView) view.findViewById(R.id.cv_background);
            tv_total_count_text = (TextView) view.findViewById(R.id.tv_total_count_text);
        }

        public void bind(final BloodGroup thisBloodGroup) {
            tv_name.setText(thisBloodGroup.getName());
            ll_background.setBackgroundColor(Color.parseColor(thisBloodGroup.getThemeColor()));
            tv_total_count.setText(String.valueOf(thisBloodGroup.getApproxTotalCount()));

            String totalCountText;
            switch (thisBloodGroup.getApproxTotalCount()) {
                case 0:
                    totalCountText = context.getString(R.string.text_zero_donor_ready);
                    break;
                case 1:
                    totalCountText = context.getString(R.string.text_one_donor_ready);
                    break;
                default:
                    totalCountText = context.getString(R.string.text_multi_donor_ready);
                    break;
            }
            tv_total_count_text.setText(totalCountText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(Constants.FragmentParameters.keyObject, thisBloodGroup);
                    Utilities.getApp(context).doUserAction(UserAction.DONOR_LIST_FRAGMENT, mBundle);
                }
            });

        }
    }
}
