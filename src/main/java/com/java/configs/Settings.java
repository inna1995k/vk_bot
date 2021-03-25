package com.java.configs;


public class Settings {
    public static String accessToken;
    public static int groupID;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        Settings.accessToken = accessToken;
    }

    public static int getGroupID() {
        return groupID;
    }

    public static void setGroupID(int groupID) {
        Settings.groupID = groupID;
    }
}
