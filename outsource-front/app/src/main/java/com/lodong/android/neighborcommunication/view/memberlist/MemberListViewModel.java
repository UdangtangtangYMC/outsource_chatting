package com.lodong.android.neighborcommunication.view.memberlist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lodong.android.neighborcommunication.repository.Repository;
import com.lodong.android.neighborcommunication.repository.RepositoryImpl;
import com.lodong.android.neighborcommunication.repository.model.MemberDTO;
import com.lodong.android.neighborcommunication.view.callback.GetMemberListCallBack;
import java.util.List;

public class MemberListViewModel extends ViewModel {
    private MutableLiveData<List<MemberDTO>> memberListML = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorML = new MutableLiveData<>();
    private Repository repository;

    public void init(){
        repository = RepositoryImpl.getInstance();
        repository.setGetMemberListCallBack(getMemberListCallBack());
    }

    public void getMemberList() {
        repository.getMemberList();
    }

    public MutableLiveData<List<MemberDTO>> getMemberListML(){
        return this.memberListML;
    }

    public MutableLiveData<Throwable> getErrorML(){
        return this.errorML;
    }

    public GetMemberListCallBack getMemberListCallBack(){
        return new GetMemberListCallBack() {
            @Override
            public void onSuccess(List<MemberDTO> memberList) {
                memberListML.setValue(memberList);
            }

            @Override
            public void onFailed(Throwable t) {
                errorML.setValue(t);
            }
        };
    }
}
