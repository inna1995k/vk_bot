package com.java.callback;

import com.java.handlers.Messager;
import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;
import lombok.SneakyThrows;

public class CallbackLongPollHandler extends CallbackApiLongPoll {

    private Messager handler;

    public CallbackLongPollHandler(VkApiClient client, GroupActor actor, Messager handler) {
        super(client, actor);
        this.handler = handler;
    }


    @SneakyThrows
    @Override
    public void messageNew(Integer groupId, Message message) {
        if (message != null && !message.isOut()) {
            if (handler == null ) return;
            handler.handle(message);
        }
    }

    @SneakyThrows
    @Override
    public void messageNew(Integer groupId, String secret, Message message) {
        if (message != null && !message.isOut()) {
            if (handler == null ) return;
            handler.handle(message);
        }
    }
}
