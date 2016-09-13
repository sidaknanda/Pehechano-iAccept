package com.iaccept.pehechano.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.iaccept.pehechano.R;
import com.iaccept.pehechano.common.Constants;
import com.iaccept.pehechano.home.account.AccountsFragment;
import com.iaccept.pehechano.home.dashboard.DashboardFragment;
import com.iaccept.pehechano.home.employees.EmployeesFragment;
import com.iaccept.pehechano.home.logout.LogoutFragment;
import com.iaccept.pehechano.home.staff.StaffFragment;
import com.iaccept.pehechano.login.LoginActivity;

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

                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:
                        SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).edit();
                        editor.remove(Constants.PREFERENCES_USERNAME);
                        editor.remove(Constants.PREFERENCES_PASSWORD);
                        editor.apply();
                        Toast.makeText(HomeActivity.this, "Logout Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        finish();
                }
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