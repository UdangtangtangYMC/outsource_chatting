package com.lodong.android.neighborcommunication.view.memberlist;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.repository.model.MemberDTO;

import java.lang.ref.WeakReference;
import java.util.List;

public class MemberListViewModel extends ViewModel {
    private MutableLiveData<List<MemberDTO>> memberListML = new MutableLiveData<>();

    public void getMemberList(){

    }
}
