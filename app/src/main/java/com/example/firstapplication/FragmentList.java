package com.example.firstapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.textview.MaterialTextView;

public class FragmentList extends Fragment {



    private MaterialTextView list_LBL_title;
    private ListView fragmentList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_iteml, container, false);

        findViews(view);
        return view;
    }


    private void findViews(View view) {
        list_LBL_title = view.findViewById(R.id.list_LBL_title);
        fragmentList = view.findViewById(R.id.fragmentList);
    }

    public ListView getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(ListView fragmentList) {
        this.fragmentList = fragmentList;
    }
    public MaterialTextView getList_LBL_title() {
        return list_LBL_title;
    }

    public void setList_LBL_title(MaterialTextView list_LBL_title) {
        this.list_LBL_title = list_LBL_title;
    }
}