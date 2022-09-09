package com.engage.myway;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation botBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botBottomNavigation = findViewById(R.id.bottom_navigation);

        //Add Menu Item

        botBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_list));
        botBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        botBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_profile));


        botBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new ActivityFragment();
                        break;
                    case 3:
                        fragment = new ProfileFragment();
                        break;
                }
                //load fragment
                loadFragment(fragment);
            }
        });
        //set notification count
//        botBottomNavigation.setCount(1,"10");

        //set home fragment initially selected
        botBottomNavigation.show(1,true);
        botBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        botBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });



    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

}