package com.styx.gta.donorblood.adapters;

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
import com.styx.gta.donorblood.base.BaseAdapter;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.constants.UserAction;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.utilities.Utilities;

import java.util.ArrayList;

/**
 * Created by amal.george on 29-11-2016.
 */

public class BloodGroupBaseAdapter extends RecyclerView.Adapter<BloodGroupBaseAdapter.ViewHolder> implements BaseAdapter<BloodGroup> {
    private final String TAG = "BloodGroupBaseAdapter";
    private final int layoutID = R.layout.item_blood_group;
    private ArrayList<BloodGroup>  list = new ArrayList<>();
    private Context context;

    @Override
    public void addItem(BloodGroup bloodGroup) {
        list.add(bloodGroup);
        notifyDataSetChanged();
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(layoutID, parent, false);
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
