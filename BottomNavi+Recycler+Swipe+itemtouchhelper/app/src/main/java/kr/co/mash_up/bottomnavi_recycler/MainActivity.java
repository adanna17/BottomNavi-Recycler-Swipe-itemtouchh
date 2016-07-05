package kr.co.mash_up.bottomnavi_recycler;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;

import kr.co.mash_up.bottomnavi_recycler.Fragment.CategoryFragment;
import kr.co.mash_up.bottomnavi_recycler.Fragment.StoryFragment;
import kr.co.mash_up.bottomnavi_recycler.Home.HomeFragment;

public class MainActivity extends AppCompatActivity  {

    private PagerAdapter adapter;
    private AHBottomNavigationViewPager viewPager;
    private AHBottomNavigation bottomNavigation;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUI();
    }

    private void initUI() {

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("홈", R.drawable.icon_smile);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("카테고리", R.drawable.icon_smile);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("스토리", R.drawable.icon_smile);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);

        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position, false);
                return true;
            }
        });

        adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new CategoryFragment());
        adapter.addFragment(new StoryFragment());
        viewPager.setAdapter(adapter);

    }

}
