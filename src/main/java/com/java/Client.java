package com.java;

import com.java.configs.Settings;
import com.java.messanger.VkMessenger;


public class Client {

    public Client(int groupID, String accessToken) {
        Settings.accessToken = accessToken;
        Settings.groupID = groupID;
    }

    public VkStarter initService() {
        return new VkStarter();
    }

    public VkMessenger getMessanger(VkStarter vkStarter) {
        return vkStarter.getVkMessenger();
    }

}
