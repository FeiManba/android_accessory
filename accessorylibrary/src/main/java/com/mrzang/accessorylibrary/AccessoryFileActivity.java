package com.mrzang.accessorylibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mrzang.accessorylibrary.adapter.PublicTabViewPagerAdapter;

import java.util.ArrayList;

/**
 * @author : mr.zang
 * description 附件检索文件类
 * 附件：
 * 1.office wps
 * 2.mp3
 * 3.mp4
 * 4.img
 * 5.zip
 * 6.tab 所有 微信 QQ
 * 7.手机存储
 * createDate: 2019-12-19 14:02
 */
public class AccessoryFileActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mReReturn;
    private TextView mTvTitle;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView mTvFileNum;
    private TextView mTvOk;
    private ArrayList<Fragment> mFragment;
    private ArrayList<String> mTabTitles;
    private PublicTabViewPagerAdapter tabViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessory_file);
        initView();
        initViewPager();
    }

    private void initViewPager() {
        mTabTitles.add("所有");
        mTabTitles.add("微信");
        mTabTitles.add("其他");
        mFragment.add(new FolderFragment());
        mFragment.add(new FolderFragment());
        mFragment.add(new FolderFragment());

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (tabViewPagerAdapter == null) {
            tabViewPagerAdapter = new PublicTabViewPagerAdapter(fragmentManager, mFragment, mTabTitles);
            mViewPager.setAdapter(tabViewPagerAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }

    private void initView() {
        mReReturn = (RelativeLayout) findViewById(R.id.re_return);
        mReReturn.setOnClickListener(this);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("附件");
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTvFileNum = (TextView) findViewById(R.id.tv_file_num);
        mTvOk = (TextView) findViewById(R.id.tv_ok);
        mTvOk.setOnClickListener(this);
        mFragment = new ArrayList<>();
        mTabTitles = new ArrayList<>();
        mFragment.clear();
        mTabTitles.clear();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.re_return) {
            this.finish();
        } else if (id == R.id.tv_ok) {

        }
    }
}
