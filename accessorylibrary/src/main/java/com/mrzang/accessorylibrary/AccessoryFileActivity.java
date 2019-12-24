package com.mrzang.accessorylibrary;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mrzang.accessorylibrary.adapter.PublicTabViewPagerAdapter;
import com.mrzang.accessorylibrary.utils.FileSearchingUtils;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

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
public class AccessoryFileActivity extends AppCompatActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {
    private RelativeLayout mReReturn;
    private TextView mTvTitle;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView mTvFileNum;
    private TextView mTvOk;
    private ArrayList<Fragment> mFragment;
    private ArrayList<String> mTabTitles;
    private PublicTabViewPagerAdapter tabViewPagerAdapter;
    private final static int RC_READ_AND_WRTIE = 102;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessory_file);
        initView();
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            initViewPager();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.read_waite_rationale),
                    RC_READ_AND_WRTIE, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void initViewPager() {
        mTabTitles.add("所有");
        mTabTitles.add("微信");
        mTabTitles.add("QQ");
        mFragment.add(FolderFragment.getInstance(0));
        mFragment.add(FolderFragment.getInstance(1));
        mFragment.add(FolderFragment.getInstance(2));

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (tabViewPagerAdapter == null) {
            tabViewPagerAdapter = new PublicTabViewPagerAdapter(fragmentManager, mFragment, mTabTitles);
            mViewPager.setAdapter(tabViewPagerAdapter);
            mViewPager.setOffscreenPageLimit(3);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FileSearchingUtils.getInstance().delCache();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            initViewPager();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setRationale(R.string.read_waite_rationale)
                    .build()
                    .show();
        }
    }
}
