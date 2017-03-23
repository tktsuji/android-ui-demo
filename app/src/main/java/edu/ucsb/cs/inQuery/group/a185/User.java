package edu.ucsb.cs.inQuery.group.a185;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static String umail, password, major, username, userLevel;
    private static Uri avatar;
    private static List<String> tags;
    private static User instance = null;
    private static final String DEFAULT_PHOTO ="android.resource://edu.ucsb.cs.cs185.group.a185/drawable/avatar";

    public User() {
        umail = null;
        password = null;
        major = null;
        username = null;
        userLevel = null;
        tags = new ArrayList<String>();
        avatar = Uri.parse(DEFAULT_PHOTO);
    }

    public static User getInstance() {
        if(instance == null) {
            instance = new User();
        }
        return instance;
    }

    public static void setUmail(String umail_arg) {
        umail = umail_arg;
    }

    public static String getUmail() {
        return umail;
    }

    public static void setPassword(String password_arg) {
        password = password_arg;
    }

    public static String getPassword() {
        return password;
    }

    public static void setMajor(String major_arg) {
        major = major_arg;
    }

    public static String getMajor() {
        return major;
    }

    public static void setUsername(String username_arg) {
        username = username_arg;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUserLevel(String userLevel_arg) {
        userLevel = userLevel_arg;
    }

    public static String getUserLevel() {
        return userLevel;
    }

    public static void setAvatar(Uri imageUri) {
        avatar = imageUri;
    }

    public static Uri getAvatar() {
        return avatar;
    }

    public static String getDefaultPhoto() {
        return DEFAULT_PHOTO;
    }

    public static List<String> getTags()  {
        return tags;
    }

    public static void setTags(ArrayList<String> updatedTags) {
        tags = new ArrayList<String>(updatedTags);
    }

    public static void addTag(String tag) {
        tags.add(tag);
    }

    public static void deleteTag(String tag) {
        tags.remove(tag);
    }

    public static void clearTag() {
        tags.clear();
    }

    public static void purgeUser() {
        instance = new User();
    }
}
