package com.java.examples;

import com.java.messanger.VkMessenger;

import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import org.springframework.stereotype.Service;


@Service
public class UpdateProcessService {

    public void update(VkMessenger vkMessenger, Message message) throws ClientException {

        if (message.getText().contains("Hy")) {
            vkMessenger.sendMessage("Bot said hy", message);
        }

        if (hasText(message)) {
            System.out.println("has Text");
        }

    }

    public static boolean hasText(Message message) {
        return !message.getText().isEmpty();
    }
}
