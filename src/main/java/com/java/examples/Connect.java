package com.java.examples;

import com.java.Client;
import com.java.VkStarter;
import com.java.handlers.Messager;
import com.java.messanger.VkMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Component
public class Connect {

    @Autowired private UpdateProcessService updateProcessService;

    private Messager messager = null;

    private void start() {
        int groupId = 203457466;
        String accessToken = "6bc146e2f6f4939e93bb872e66c751fe410aa79abc9add5f3d5c76d2faf0e779363fd1dab1e8ffb4d41c5";

        Client client = new Client(groupId, accessToken);
        VkStarter vkStarter = client.initService();
        VkMessenger vkMessenger = client.getMessanger(vkStarter);



        messager = message -> {
            updateProcessService.update(vkMessenger, message);
            return message;
        };

        try {
            vkStarter.startUpdates(messager);
        }catch (NullPointerException e){
            System.out.println("Catch");
        }
    }


    @PostConstruct
    private void run() {
        start();
    }
}
