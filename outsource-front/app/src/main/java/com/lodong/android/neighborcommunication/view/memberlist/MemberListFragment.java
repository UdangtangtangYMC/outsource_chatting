package com.lodong.android.neighborcommunication.view.memberlist;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.databinding.FragmentMemberListBinding;
import com.lodong.android.neighborcommunication.view.adapter.MemberListAdapter;

public class MemberListFragment extends Fragment {
    private final String TAG = MemberListFragment.class.getSimpleName();
    private FragmentMemberListBinding binding;
    private MemberListViewModel viewModel;
    private MemberListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false);
        viewModel = new ViewModelProvider(this).get(MemberListViewModel.class);
        Log.d(TAG, "onCreateView");
        init();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getMemberList();
    }

    private void init(){
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new MemberListAdapter();
        binding.recyclerview.setAdapter(adapter);
    }

    private void getMemberList(){
        viewModel.getMemberList();
    }
}