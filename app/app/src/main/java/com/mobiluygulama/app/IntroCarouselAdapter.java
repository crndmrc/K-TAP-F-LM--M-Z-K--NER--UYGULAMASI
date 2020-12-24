package com.mobiluygulama.app;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class IntroCarouselAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public IntroCarouselAdapter(FragmentManager supportFragmentManager, List<Fragment> list) {
        super(supportFragmentManager);
        this.fragmentList = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
