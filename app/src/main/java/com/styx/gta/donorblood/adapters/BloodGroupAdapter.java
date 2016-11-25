package com.styx.gta.donorblood.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.models.BloodGroup;

import java.util.List;

/**
 * Created by amal.george on 25-11-2016.
 */

public class BloodGroupAdapter extends RecyclerView.Adapter<BloodGroupAdapter.ViewHolder> {
    private List<BloodGroup> list;
    private Context mContext;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(BloodGroup thisBloodGroup);
    }

    public BloodGroupAdapter(List<BloodGroup> list, Context mContext, OnItemClickListener mListener) {
        this.list = list;
        this.mContext = mContext;
        this.mListener = mListener;
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
        holder.bind(thisBloodGroup, mListener);
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

        public void bind(final BloodGroup thisBloodGroup, final OnItemClickListener listener) {
            tv_name.setText(thisBloodGroup.getName());
            ll_background.setBackgroundColor(Color.parseColor(thisBloodGroup.getThemeColor()));
            tv_total_count.setText(String.valueOf(thisBloodGroup.getApproxTotalCount()));

            String totalCountText;
            switch (thisBloodGroup.getApproxTotalCount()) {
                case 0:
                    totalCountText = mContext.getString(R.string.text_zero_donor_ready);
                    break;
                case 1:
                    totalCountText = mContext.getString(R.string.text_one_donor_ready);
                    break;
                default:
                    totalCountText = mContext.getString(R.string.text_multi_donor_ready);
                    break;
            }
            tv_total_count_text.setText(totalCountText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(thisBloodGroup);
                }
            });

        }
    }
}
