package edu.ucsb.cs.cs185.group.a185;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tricia on 3/9/17.
 */

public class User {
    private static String umail, password, major, username, gradYear;
    private static List<String> tags;
    private static User instance = null;

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

    public static void setGradYear(String gradYear_arg) {
        gradYear = gradYear_arg;
    }

    public static String getGradYear() {
        return gradYear;
    }

    public static List<String> getTags()  {
        return tags;
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
}
