package com.global.ustewardBase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用FragmentPagerAdapter
 * <p>
 * Author: Guangkuo
 * Email: dingguangkuo@163.com
 * Date: 2018-2-2  10:29
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private List<Fragment> mFragments = new ArrayList<>();

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public BaseFragmentPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        mTitles = titles;
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles != null ? mTitles[position] : null;
    }
}