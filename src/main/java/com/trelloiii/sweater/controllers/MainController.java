package com.trelloiii.sweater.controllers;

import com.trelloiii.sweater.domain.Message;
import com.trelloiii.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    MessageRepository messageRepository;
    @RequestMapping
    public String greeting(){
        return "greeting";
    }
    @GetMapping("/main")
    public String index(@RequestParam(required = false,defaultValue = "World") String name,
                        Map<String,Object> model){
        Iterable<Message> messages=messageRepository.findAll();
        model.put("messages",messages);
        return "main";
    }
    @PostMapping("/main")
    public String add(@RequestParam String text,@RequestParam String tag,Map<String,Object> model){
        Message message=new Message(text,tag);
        messageRepository.save(message);
        Iterable<Message> messages=messageRepository.findAll();
        model.put("messages",messages);
        return "main";
    }
    @PostMapping("/filter")
    public String search(@RequestParam String text,Map<String,Object> model){
        Iterable<Message> messages;
        if(text!=null&&!text.isEmpty()) {
            messages = messageRepository.findByTag(text);
        }
        else{
            messages=messageRepository.findAll();
        }
        model.put("messages",messages);
        return "main";
    }
}
