package com.styx.gta.donorblood.fragments.chat.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.fragments.chat.adapters.CustomMessageRecyclerAdapter;
import com.styx.gta.donorblood.fragments.chat.presenter.FirebaseChatMessagePresenterImpl;

/**
 * Created by Filip on 25/02/2016.
 */
public class ChatFragment extends BaseFragment {
    private RecyclerView mMessagesListView;
    private CustomMessageRecyclerAdapter adapter;
    public static final String TAG = "ChatFragment";

    private FirebaseChatMessagePresenterImpl presenter;

    @Override
    protected void initUI() {
        setScreenLayout(R.layout.fragment_chat_service);
        setRoot(true);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        mMessagesListView = (RecyclerView) rootView.findViewById(R.id.chat_recycler_view);
        adapter = new CustomMessageRecyclerAdapter();
        adapter.request();

        mMessagesListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMessagesListView.setHasFixedSize(true);
        mMessagesListView.setItemAnimator(new DefaultItemAnimator());
        mMessagesListView.setAdapter(adapter);
        presenter = new FirebaseChatMessagePresenterImpl();
    }

}