package com.trelloiii.sweater.controllers;

import com.trelloiii.sweater.domain.Message;
import com.trelloiii.sweater.domain.User;
import com.trelloiii.sweater.repository.MessageRepository;
import com.trelloiii.sweater.service.UserService;
import freemarker.ext.servlet.FreemarkerServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.trelloiii.sweater.controllers.UtilsController.getErrors;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Value("${upload.path}")
    private String uploadPath;
    @RequestMapping
    public String greeting(){
        return "greeting";
    }
    @GetMapping("/main")
    public String index(@RequestParam(required = false) String filter,
                        @AuthenticationPrincipal User user,
                        Model model){
        Iterable<Message> messages=messageRepository.findAll();
        if(filter!=null&&!filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        }
        else{
            messages=messageRepository.findAll();
        }
        userRef(user, model);
        model.addAttribute("messages",messages);
        model.addAttribute("filter",filter);
        return "main";
    }

    private void userRef(User user, Model model) {
        String ref = "/user/profile";
        if (userService.isAdmin(user))
            ref = "/user";
        model.addAttribute("ref", ref);
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam(name = "file") MultipartFile file
            ) throws IOException {
        if(bindingResult.hasErrors()){
            getErrors(bindingResult, model);
            model.addAttribute("message",message);
        }else {
            message.setAuthor(user);
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFileName));
                message.setFilename(resultFileName);
            }
            model.addAttribute("message",null);
            messageRepository.save(message);
        }
        userRef(user, model);
        Iterable<Message> messages=messageRepository.findAll();
        model.addAttribute("messages",messages);
        return "main";
    }
}
