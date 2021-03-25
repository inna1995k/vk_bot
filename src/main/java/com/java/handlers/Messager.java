package com.java.handlers;

import com.vk.api.sdk.objects.messages.Message;


public interface Messager {
    Message handle(Message message) throws Exception;
}
