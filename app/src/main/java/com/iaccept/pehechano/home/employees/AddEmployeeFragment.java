package com.iaccept.pehechano.home.employees;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.iaccept.pehechano.R;
import com.iaccept.pehechano.common.Constants;
import com.iaccept.pehechano.common.DatePickerFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEmployeeFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = AddEmployeeFragment.class.getName();

    private View fragmentView;
    private Spinner spinnerGender, spinnerCountry, spinnerBloodGroup, spinnerEmployerType, spinnerEmploymentCategory, spinnerGovtIdType;
    private ImageButton buttonDatePickerDOB, buttonDatePickerJoiningDate, buttonPhotoPickerProfilePic, buttonPhotoPickerGovtId;

    public AddEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_add_employee, container, false);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupUI();
    }

    private void setupUI() {
        buttonDatePickerDOB = (ImageButton) fragmentView.findViewById(R.id.imageButtonDatePickerDOB);
        buttonDatePickerDOB.setOnClickListener(this);
        buttonDatePickerJoiningDate = (ImageButton) fragmentView.findViewById(R.id.imageButtonDatePickerJoiningDate);
        buttonDatePickerJoiningDate.setOnClickListener(this);
        buttonPhotoPickerProfilePic = (ImageButton) fragmentView.findViewById(R.id.imageButtonPhotoPickerProfilePic);
        buttonPhotoPickerProfilePic.setOnClickListener(this);
        buttonPhotoPickerGovtId = (ImageButton) fragmentView.findViewById(R.id.imageButtonPhotoPickerGovtId);
        buttonPhotoPickerGovtId.setOnClickListener(this);

        spinnerGender = (Spinner) fragmentView.findViewById(R.id.spinnerGender);
        ArrayAdapter<String> arrayAdapterGender = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Constants.GENDER);
        spinnerGender.setAdapter(arrayAdapterGender);
        spinnerBloodGroup = (Spinner) fragmentView.findViewById(R.id.spinnerBloodGroup);
        ArrayAdapter<String> arrayAdapterBloodGroup = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Constants.BLOOD_GROUP);
        spinnerBloodGroup.setAdapter(arrayAdapterBloodGroup);
        spinnerCountry = (Spinner) fragmentView.findViewById(R.id.spinnerCountry);
        //ArrayAdapter<String> arrayAdapterCountry = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Constants.GENDER);
        //spinnerCountry.setAdapter(arrayAdapterCountry);
        spinnerEmployerType = (Spinner) fragmentView.findViewById(R.id.spinnerEmployerType);
        ArrayAdapter<String> arrayAdapterEmployerType = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Constants.EMPLOYER_TYPE);
        spinnerEmployerType.setAdapter(arrayAdapterEmployerType);
        spinnerEmploymentCategory = (Spinner) fragmentView.findViewById(R.id.spinnerEmploymentCategory);
        ArrayAdapter<String> arrayAdapterEmploymentCategory = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Constants.EMPLOYER_CATEGORY);
        spinnerEmploymentCategory.setAdapter(arrayAdapterEmploymentCategory);
        spinnerGovtIdType = (Spinner) fragmentView.findViewById(R.id.spinnerGovtIdType);
        ArrayAdapter<String> arrayAdapterGovtIdType = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Constants.GOVERNMENT_ID_TYPE);
        spinnerGovtIdType.setAdapter(arrayAdapterGovtIdType);
    }

    public void showDatePicker() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private Uri outputFileUri;

    private void openImageIntent() {

        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "Pehechano" + File.separator);
        root.mkdirs();
        final String fname = "ImageP";
        final File sdImageMainDirectory = new File(root, fname);
        outputFileUri = Uri.fromFile(sdImageMainDirectory);

        // Camera.
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getActivity().getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

        startActivityForResult(chooserIntent, 111);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 111) {
                final boolean isCamera;
                if (data == null) {
                    isCamera = true;
                } else {
                    final String action = data.getAction();
                    isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
                }

                Uri selectedImageUri;
                if (isCamera) {
                    selectedImageUri = outputFileUri;
                } else {
                    selectedImageUri = data.getData();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageButtonDatePickerDOB || view.getId() == R.id.imageButtonDatePickerJoiningDate) {
            showDatePicker();
        } else if (view.getId() == R.id.imageButtonPhotoPickerProfilePic || view.getId() == R.id.imageButtonPhotoPickerGovtId) {
            openImageIntent();
        }
    }
}