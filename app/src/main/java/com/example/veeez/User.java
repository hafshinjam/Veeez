package com.example.veeez;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("FullAddress")
    private String fullAddress;

    @SerializedName("Email")
    private String email;

    @SerializedName("IntroCode")
    private String introCode;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("StateId")
    private int stateId;

    @SerializedName("AccessCode")
    private String accessCode;

    @SerializedName("BirthString")
    private String birthString;

    @SerializedName("Birhtdate")
    private String birhtdate;

    @SerializedName("Gender")
    private String gender;

    @SerializedName("Mobile")
    private String mobile;

    @SerializedName("GenderId")
    private int genderId;

    @SerializedName("Score")
    private int score;

    @SerializedName("Credit")
    private int credit;

    @SerializedName("CityId")
    private int cityId;

    @SerializedName("Phone")
    private String phone;

    @SerializedName("ProfileImage")
    private String profileImage;

    @SerializedName("LastName")
    private String lastName;

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFullAddress() {
        return fullAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getIntroCode() {
        return introCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getStateId() {
        return stateId;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public String getBirthString() {
        return birthString;
    }

    public String getBirhtdate() {
        return birhtdate;
    }

    public String getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }

    public int getGenderId() {
        return genderId;
    }

    public int getScore() {
        return score;
    }

    public int getCredit() {
        return credit;
    }

    public int getCityId() {
        return cityId;
    }

    public String getPhone() {
        return phone;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullAddress);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(introCode);
        dest.writeString(firstName);
        dest.writeInt(stateId);
        dest.writeString(accessCode);
        dest.writeString(birthString);
        dest.writeString(birhtdate);
        dest.writeString(gender);
        dest.writeString(mobile);
        dest.writeInt(genderId);
        dest.writeInt(score);
        dest.writeInt(credit);
        dest.writeInt(cityId);
        dest.writeString(phone);
        dest.writeString(profileImage);
    }

    public User() {
    }

    public User(Parcel in) {
        fullAddress = in.readString();
        email = in.readString();
        introCode = in.readString();
        firstName = in.readString();
        stateId = in.readInt();
        accessCode = in.readString();
        birthString = in.readString();
        birhtdate = in.readString();
        gender = in.readString();
        mobile = in.readString();
        genderId = in.readInt();
        score = in.readInt();
        credit = in.readInt();
        cityId = in.readInt();
        phone = in.readString();
        profileImage = in.readString();
        lastName = in.readString();
    }
}