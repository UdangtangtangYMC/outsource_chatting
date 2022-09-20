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
import android.widget.Toast;

import com.lodong.android.neighborcommunication.R;
import com.lodong.android.neighborcommunication.databinding.FragmentMemberListBinding;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.view.adapter.MemberListAdapter;

import java.util.List;

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
        viewModel.setParent(getActivity());
        binding.setViewModel(viewModel);
        Log.d(TAG, "onCreateView");

        init();
        settingLiveData();

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
        adapter.setOnMemberClickListener(getOnMemberClickListener());
        binding.recyclerview.setAdapter(adapter);
        viewModel.init();
    }

    private void settingLiveData(){
        viewModel.getMemberListML().observe(getViewLifecycleOwner(), memberList -> {
            //화면에 뿌려줌
            settingMemberList(memberList);
        });

        viewModel.getErrorML().observe(getViewLifecycleOwner(), throwable -> {
            //에러 출력
            Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_SHORT).show();
        });
    }

    private void getMemberList(){
        viewModel.getMemberList();
    }

    private void settingMemberList(List<MemberDTO> memberList){
        this.adapter.changeMemberListAdapter(memberList);
    }

    private OnMemberClickListener getOnMemberClickListener(){
        return memberDTO -> {
            viewModel.showOpenMemberSettingDialog(memberDTO);
        };
    }

    public interface OnMemberClickListener{
        public void onClick(MemberDTO memberDTO);
    }
}