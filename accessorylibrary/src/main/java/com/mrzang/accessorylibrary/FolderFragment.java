package com.mrzang.accessorylibrary;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mrzang.accessorylibrary.adapter.FolderDataRecycleAdapter;


/**
 * Created by yis on 2018/4/17.
 */

public class FolderFragment extends Fragment {
    private View view;
    private ItemClickListener itemClickListener;
    private FolderDataRecycleAdapter pptListAdapter;
    private RecyclerView mRvDoc;
    private RelativeLayout mReNoSourceHint;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_file_searching_list, null);
            initView(view);
            initData();
        }

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        //        Bundle bundle = this.getArguments();
        //        if (bundle != null) {
        //            ArrayList<FileModel> data = bundle.getParcelableArrayList("file_data");
        //            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //            //设置RecyclerView 布局
        //            mRvDoc.setLayoutManager(linearLayoutManager);
        //            if (data != null && data.size() > 0) {
        //                Collections.sort(data);
        //            }
        //            pptListAdapter = new FolderDataRecycleAdapter(getActivity(),
        //                    data);
        //            pptListAdapter.setOnItemClickListener(itemClickListener);
        //            mRvDoc.setAdapter(pptListAdapter);
        //            if (data == null || data.size() == 0) {
        //                mReNoSourceHint.setVisibility(View.VISIBLE);
        //            } else {
        //                mReNoSourceHint.setVisibility(View.GONE);
        //            }
        //        }
    }

    public void changeItemCheck(String fileId, boolean select) {
        if (!TextUtils.isEmpty(fileId) && pptListAdapter != null) {
            pptListAdapter.changeItemCheck(fileId, select);
        }
    }

    private void initView(View view) {
        mRvDoc = (RecyclerView) view.findViewById(R.id.rv_doc);
        mReNoSourceHint = (RelativeLayout) view.findViewById(R.id.re_no_source_hint);
    }
}
