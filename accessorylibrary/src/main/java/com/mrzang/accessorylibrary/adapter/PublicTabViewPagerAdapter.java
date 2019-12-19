package com.mrzang.accessorylibrary.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 选项卡tab
 */
public class PublicTabViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;//fragment列表
    private List<String> mTitles;//tab名的列表

    public List<Fragment> getFragments() {
        return mFragments;
    }

    public PublicTabViewPagerAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {
        super(fm);
        this.mFragments = list_fragment;
        this.mTitles = list_Title;
    }

    @Override
    @NonNull
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position % mTitles.size());
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
