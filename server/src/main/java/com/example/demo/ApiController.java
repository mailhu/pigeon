package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @PostMapping(value = "/mailbox", produces = "application/json;charset=UTF-8")
    public String mailbox(DataPack pack) {
        return PushService.push(pack.getSender(), pack.getReceiver(), pack.getContent());
    }

}
