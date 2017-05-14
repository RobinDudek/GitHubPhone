package com.heisenbear.githubphone;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heisenbear.githubphone.gist.GistFragment;
import com.heisenbear.githubphone.home.HomeFragment;
import com.heisenbear.githubphone.repo.RepoFragment;
import com.heisenbear.githubphone.search.SearchFragment;
import com.heisenbear.githubphone.user.User;
import com.heisenbear.githubphone.user.UserFragment;
import com.heisenbear.githubphone.utils.NonSwipeableViewPager;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        NonSwipeableViewPager mViewPager = (NonSwipeableViewPager) findViewById(R.id.container);

        Intent intent = getIntent();
        if (mViewPager != null) {
            mViewPager.setAdapter(mSectionsPagerAdapter);
            if (intent.getIntExtra("Fragment", -1) != -1) {
                mViewPager.setCurrentItem(1);
            }
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(mViewPager);
            tabLayout.getTabAt(0).setIcon(R.drawable.search);
            tabLayout.getTabAt(1).setIcon(R.drawable.user);
            tabLayout.getTabAt(2).setIcon(R.drawable.gist);
            tabLayout.getTabAt(3).setIcon(R.drawable.user);
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return SearchFragment.newInstance();
                case 1:
                    return RepoFragment.newInstance();
                case 2:
                    return GistFragment.newInstance();
                case 3:
                    return UserFragment.newInstance();
                default:
                    return HomeFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
