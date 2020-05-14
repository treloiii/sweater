package com.trelloiii.sweater.controllers;

import com.trelloiii.sweater.domain.Message;
import com.trelloiii.sweater.domain.User;
import com.trelloiii.sweater.repository.MessageRepository;
import freemarker.ext.servlet.FreemarkerServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    MessageRepository messageRepository;
    @Value("${upload.path}")
    private String uploadPath;
    @RequestMapping
    public String greeting(){
        return "greeting";
    }
    @GetMapping("/main")
    public String index(@RequestParam(required = false) String filter,
                        Model model){
        Iterable<Message> messages=messageRepository.findAll();
        if(filter!=null&&!filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        }
        else{
            messages=messageRepository.findAll();
        }
        model.addAttribute("messages",messages);
        model.addAttribute("filter",filter);
        return "main";
    }
    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam(name = "file") MultipartFile file,
            Model model) throws IOException {
        Message message=new Message(text,tag,user);
        if(file!=null&&!file.getOriginalFilename().isEmpty()){
            File uploadDir=new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }
            String uuidFile= UUID.randomUUID().toString();
            String resultFileName=uuidFile+"."+file.getOriginalFilename();

            file.transferTo(new File(uploadPath+"/"+resultFileName));
            message.setFilename(resultFileName);
        }
        messageRepository.save(message);
        Iterable<Message> messages=messageRepository.findAll();
        model.addAttribute("messages",messages);
        return "main";
    }

}
