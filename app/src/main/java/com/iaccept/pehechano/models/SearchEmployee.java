package com.iaccept.pehechano.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchEmployee implements Parcelable {

    private String emploeeName;
    private String pehechanoId;
    private String profilePic;
    private String mobileNumber;

    public static final String SEARCH_NAME = "emploeeName";
    public static final String SEARCH_ID = "pehechanoId";
    public static final String SEARCH_PIC = "profilePic";
    public static final String SEARCH_MOBILE = "mobileNumber";

    public SearchEmployee() {
    }

    protected SearchEmployee(Parcel in) {
        emploeeName = in.readString();
        pehechanoId = in.readString();
        profilePic = in.readString();
        mobileNumber = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(emploeeName);
        dest.writeString(pehechanoId);
        dest.writeString(profilePic);
        dest.writeString(mobileNumber);
    }

    public String getEmploeeName() {
        return emploeeName;
    }

    public void setEmploeeName(String emploeeName) {
        this.emploeeName = emploeeName;
    }

    public String getPehechanoId() {
        return pehechanoId;
    }

    public void setPehechanoId(String pehechanoId) {
        this.pehechanoId = pehechanoId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SearchEmployee> CREATOR = new Parcelable.Creator<SearchEmployee>() {
        @Override
        public SearchEmployee createFromParcel(Parcel in) {
            return new SearchEmployee(in);
        }

        @Override
        public SearchEmployee[] newArray(int size) {
            return new SearchEmployee[size];
        }
    };
}