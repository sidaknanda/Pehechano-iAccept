package com.iaccept.pehechano.home.employees;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.iaccept.pehechano.R;
import com.iaccept.pehechano.home.HomeActivity;

public class EmployeesFragment extends Fragment {

    public static final String TAG = EmployeesFragment.class.getName();

    private View fragmentView;
    private Button addEmployee;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_employees, container, false);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupUI();
    }

    private void setupUI() {
        addEmployee = (Button) fragmentView.findViewById(R.id.buttonAddEmployee);
        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).showFragment(new AddEmployeeFragment(), AddEmployeeFragment.TAG);
            }
        });
    }
}