package com.java;

import com.java.callback.CallbackLongPollHandler;
import com.java.configs.Settings;
import com.java.handlers.Messager;
import com.java.messanger.VkMessenger;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.stereotype.Component;

@Component
public class VkStarter {

    private static VkApiClient vkApiClient;
    private static GroupActor groupActor;
    public Messager messager = null;
    public VkMessenger vkMessenger;
    private final TransportClient transportClient = new HttpTransportClient();

    public VkStarter() {
        groupActor = init();
        vkMessenger = new VkMessenger();
        vkMessenger.setGroupActor(groupActor);
        vkMessenger.setVkApiClient(vkApiClient);
    }

    public void startUpdates(Messager messageHandler) {
        this.messager = messageHandler;

        try {
            if (!vkApiClient.groups().getLongPollSettings(groupActor, groupActor.getGroupId()).execute().getIsEnabled()) {
                vkApiClient.groups().setLongPollSettings(groupActor, groupActor.getGroupId()).enabled(true).messageNew(true).wallPostNew(true).execute();
            }


            CallbackLongPollHandler handler = new CallbackLongPollHandler(vkApiClient, groupActor, messageHandler);
            handler.run();
        } catch (ClientException | ApiException ex ) {
            System.out.println("Restart connection to VK API..");
            startUpdates(messageHandler);
        }
    }


    public GroupActor init() {
        int groupID = getGroupID();
        String accessToken = getAccessToken();

        vkApiClient = new VkApiClient(this.transportClient);
        return new GroupActor(groupID, accessToken);
    }

    public VkMessenger getVkMessenger() {
        return vkMessenger;
    }

    private static String getAccessToken() {
        return Settings.getAccessToken();
    }

    private static Integer getGroupID() {
        return Settings.getGroupID();
    }
}
