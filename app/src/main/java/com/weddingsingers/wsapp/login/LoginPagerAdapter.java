package com.weddingsingers.wsapp.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class LoginPagerAdapter extends FragmentPagerAdapter {

    public final static int LOGIN_FRAGMENT = 0;
    public final static int SIGN_UP_FRAGMENT = 1;

    int tabCount;
    private String tabTitles[] = new String[]{"Login", "Sign Up"};

    public LoginPagerAdapter(FragmentManager fm,int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case LOGIN_FRAGMENT:
                return new DeprecatedLoginFragment();
            case SIGN_UP_FRAGMENT:
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
