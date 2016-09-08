package com.iaccept.pehechano.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iaccept.pehechano.R;
import com.iaccept.pehechano.home.account.AccountsFragment;
import com.iaccept.pehechano.home.dashboard.DashboardFragment;
import com.iaccept.pehechano.home.employees.EmployeesFragment;
import com.iaccept.pehechano.home.logout.LogoutFragment;
import com.iaccept.pehechano.home.staff.StaffFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = HomeActivity.class.getName();

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView listDashboard;

    private AccountsFragment accountsFragment;
    private DashboardFragment dashboardFragment;
    private EmployeesFragment employeesFragment;
    private LogoutFragment logoutFragment;
    private StaffFragment staffFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupViews();

        setSupportActionBar(toolbar);

        setupDrawer();

        setupDrawerList();

        setupFragments();
    }

    private void setupFragments() {
        showFragment(new EmployeesFragment(), null);
    }

    private void setupViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listDashboard = (ListView) findViewById(R.id.listViewDashboard);
    }

    private void setupDrawer() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                syncState();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void setupDrawerList() {
        ArrayList<String> drawerItems = new ArrayList<>();
        drawerItems.add("Accout");
        drawerItems.add("Dashboard");
        drawerItems.add("Employees");
        drawerItems.add("Staff");
        drawerItems.add("Logout");

        DrawerListAdapter drawerListAdapter = new DrawerListAdapter(this, drawerItems);
        listDashboard.setAdapter(drawerListAdapter);
        listDashboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*switch (position) {
                    case 0:
                        RequestFinishEvent requestFinishEvent1 = new RequestFinishEvent();
                        requestFinishEvent1.setRequestResponse(null);
                        EventBus.getDefault().post(requestFinishEvent1);

                        if (viewPagerAdapter.getItem(0) instanceof RequestFragment) {
                            drawerLayout.closeDrawers();
                            break;
                        }
                        viewPagerAdapter.setFragments(requestFragment, responseFragment);
                        viewPager.setCurrentItem(0);
                        drawerLayout.closeDrawers();
                        break;
                    case 1:

                        RequestFinishEvent requestFinishEvent2 = new RequestFinishEvent();
                        requestFinishEvent2.setRequestResponse(null);
                        EventBus.getDefault().post(requestFinishEvent2);

                        if (viewPagerAdapter.getItem(0) instanceof GcmFcmFragment) {
                            drawerLayout.closeDrawers();
                            break;
                        }
                        viewPagerAdapter.setFragments(gcmFcmFragment, responseFragment);

                        viewPager.setCurrentItem(0);
                        drawerLayout.closeDrawers();
                        break;

                    case 2:
                        drawerLayout.closeDrawers();
                        Intent intentSettings = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(intentSettings);
                        break;

                    case 3:
                        drawerLayout.closeDrawers();
                        Intent intentAbout = new Intent(getApplicationContext(), AboutActivity.class);
                        startActivity(intentAbout);
                        break;
                }*/
            }
        });
    }

    public void showFragment(Fragment fragment, String tagIfAddToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeContainer, fragment);
        if (tagIfAddToBackStack != null) {
            fragmentTransaction.addToBackStack(tagIfAddToBackStack);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
}