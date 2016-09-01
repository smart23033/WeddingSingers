package com.weddingsingers.wsapp.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class LoginPagerAdapter extends FragmentPagerAdapter {

    public final static int FRAG_LOGIN = 0;
    public final static int FRAG_SIGN_UP = 1;

    int tabCount;
    private String tabTitles[] = new String[]{"Login", "Sign Up"};

    public LoginPagerAdapter(FragmentManager fm,int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case FRAG_LOGIN:
                return new DeprecatedLoginFragment();
            case FRAG_SIGN_UP:
                return new DeprecatedSignUpFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
