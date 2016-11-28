package com.styx.gta.donorblood.fragments.chat.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.fragments.chat.presenter.MessagePresenterImpl;
import com.styx.gta.donorblood.models.Message;

import java.util.ArrayList;

public class MessageAdapterImpl extends RecyclerView.Adapter<MessageAdapterImpl.ViewHolder> implements MessageAdapter {
    private final ArrayList<Message> mMessageList = new ArrayList<>();
    private final MessagePresenterImpl presenter;

    public MessageAdapterImpl() {
        presenter = new MessagePresenterImpl(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message current = mMessageList.get(position);
        holder.mAuthorTextView.setText(current.getAuthor());
        holder.mMessageTextView.setText(current.getMessage());
        holder.mEmojiTextView.setText(current.getEmoji());
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public void addItem(Message message) {
        mMessageList.add(message);
        notifyDataSetChanged();
    }

    @Override
    public void request() {
        presenter.requestMessages();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mAuthorTextView;
        private TextView mMessageTextView;
        private TextView mEmojiTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mAuthorTextView = (TextView) itemView.findViewById(R.id.message_author);
            mMessageTextView = (TextView) itemView.findViewById(R.id.message_value);
            mEmojiTextView = (TextView) itemView.findViewById(R.id.message_emoji);
        }
    }
}
