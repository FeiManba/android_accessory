package com.mrzang.accessorylibrary;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrzang.accessorylibrary.adapter.FolderDataRecycleAdapter;
import com.mrzang.accessorylibrary.utils.AccessorySuffixUtils;
import com.mrzang.accessorylibrary.utils.FileSearchingUtils;
import com.mrzang.accessorylibrary.utils.SourceUrlUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;


/**
 * Created by yis on 2018/4/17.
 */

public class FolderFragment extends Fragment {
    private ProgressDialog mDialog;
    private View view;
    private ItemClickListener itemClickListener;
    private FolderDataRecycleAdapter pptListAdapter;
    private RecyclerView mRvDoc;
    private RelativeLayout mReNoSourceHint;
    private static final String FLAG_TAB_INDEX = "FLAG_TAB_INDEX";
    /**
     * 0 所有
     * 1 微信
     * 2 QQ
     */
    private int flagTabIndex;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public static FolderFragment getInstance(@IntRange(from = 0, to = 2) int tabType) {
        FolderFragment folderFragment = new FolderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FolderFragment.FLAG_TAB_INDEX, tabType);
        folderFragment.setArguments(bundle);
        return folderFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            flagTabIndex = bundle.getInt(FLAG_TAB_INDEX, 0);
        }
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

    private Handler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        WeakReference<Fragment> weakReference;

        MyHandler(Fragment fragment) {
            weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            FolderFragment folderFragment = (FolderFragment) weakReference.get();
            if (msg.what == 0) {
                folderFragment.initAccessoryData();
            }
        }
    }

    private void initAccessoryData() {
        if (flagTabIndex == 0) {
            closeProgressDialog();
            ArrayList<FileModel> allChatFileModels = FileSearchingUtils.getInstance().getAllChatFileModels();
            if (allChatFileModels == null || allChatFileModels.size() == 0) {
                mReNoSourceHint.setVisibility(View.VISIBLE);
                mRvDoc.setVisibility(View.GONE);
            } else {
                mReNoSourceHint.setVisibility(View.GONE);
                mRvDoc.setVisibility(View.VISIBLE);
                mRvDoc.setHasFixedSize(true);
                mRvDoc.setLayoutManager(new LinearLayoutManager(getActivity()));
                pptListAdapter = new FolderDataRecycleAdapter(getActivity(), allChatFileModels);
                mRvDoc.setAdapter(pptListAdapter);
            }
        }

        if (flagTabIndex == 2) {
            closeProgressDialog();
            ArrayList<FileModel> qqChatFileModels = FileSearchingUtils.getInstance().getQqChatFileModels();
            if (qqChatFileModels == null || qqChatFileModels.size() == 0) {
                mReNoSourceHint.setVisibility(View.VISIBLE);
                mRvDoc.setVisibility(View.GONE);
            } else {
                mReNoSourceHint.setVisibility(View.GONE);
                mRvDoc.setVisibility(View.VISIBLE);
                mRvDoc.setHasFixedSize(true);
                mRvDoc.setLayoutManager(new LinearLayoutManager(getActivity()));
                pptListAdapter = new FolderDataRecycleAdapter(getActivity(), qqChatFileModels);
                mRvDoc.setAdapter(pptListAdapter);
            }
        }

        if (flagTabIndex == 1) {
            closeProgressDialog();
            ArrayList<FileModel> weChatFileModels = FileSearchingUtils.getInstance().getWeChatFileModels();
            if (weChatFileModels == null || weChatFileModels.size() == 0) {
                mReNoSourceHint.setVisibility(View.VISIBLE);
                mRvDoc.setVisibility(View.GONE);
            } else {
                mReNoSourceHint.setVisibility(View.GONE);
                mRvDoc.setVisibility(View.VISIBLE);
                mRvDoc.setHasFixedSize(true);
                mRvDoc.setLayoutManager(new LinearLayoutManager(getActivity()));
                pptListAdapter = new FolderDataRecycleAdapter(getActivity(), weChatFileModels);
                mRvDoc.setAdapter(pptListAdapter);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        showProgressDialog("正在加载请稍后");
        if (flagTabIndex == 0) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    FileSearchingUtils.getInstance().recursionFile(new File(Environment.getExternalStorageDirectory().toString() + SourceUrlUtils.WeChat),
                            2, AccessorySuffixUtils.ALL);
                    FileSearchingUtils.getInstance().recursionFile(new File(Environment.getExternalStorageDirectory().toString() + SourceUrlUtils.QQ),
                            2, AccessorySuffixUtils.ALL);
                    mHandler.sendEmptyMessage(0);
                }
            }).start();
        }

        if (flagTabIndex == 1) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FileSearchingUtils.getInstance().recursionFile(new File(Environment.getExternalStorageDirectory().toString() + SourceUrlUtils.WeChat),
                            0, AccessorySuffixUtils.ALL);
                    mHandler.sendEmptyMessage(0);
                }
            }).start();
        }


        if (flagTabIndex == 2) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FileSearchingUtils.getInstance().recursionFile(new File(Environment.getExternalStorageDirectory().toString() + SourceUrlUtils.QQ),
                            1, AccessorySuffixUtils.ALL);
                    mHandler.sendEmptyMessage(0);
                }
            }).start();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        closeProgressDialog();
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

    /**
     * 显示进度提示
     *
     * @param strMsg
     */
    public void showProgressDialog(String strMsg) {
        if (mDialog != null && mDialog.isShowing()) return;
        if (strMsg == null) return;
        if (mDialog == null) {
            mDialog = new ProgressDialog(getActivity());
        }
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setMessage(strMsg);
        mDialog.show();
    }

    /**
     * 关闭进度提示
     */
    public void closeProgressDialog() {
        if (mDialog == null) return;
        if (mDialog.isShowing())
            mDialog.dismiss();
    }
}
