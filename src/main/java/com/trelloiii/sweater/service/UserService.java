package com.trelloiii.sweater.service;

import com.trelloiii.sweater.domain.Role;
import com.trelloiii.sweater.domain.User;
import com.trelloiii.sweater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyMailSender myMailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user= userRepository.findByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("Bad credentials");
        }
        return user;
    }
    private void sendMessage(User user){
        String message="Hello activation link: http://localhost:8080/activate/"+user.getActivationCode();
        myMailSender.send(user.getEmail(),"Activation code",message);
    }
    public boolean isAdmin(User user){
        return user.getRoles().contains(Role.ADMIN);
    }
    public boolean addUser(User user){
        User fromDb=userRepository.findByUsername(user.getUsername());
        if(fromDb!=null){
            return true;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        if(!StringUtils.isEmpty(user.getUsername())){
            sendMessage(user);
        }
        return false;
    }

    public boolean activateUser(String code) {
        User user=userRepository.findByActivationCode(code);
        if(user==null)
            return false;
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void edit(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles= Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        System.out.println(form);
        user.getRoles().clear();
        for (String key:form.keySet()){
            if(roles.contains(key))
                user.getRoles().add(Role.valueOf(key));
        }
        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        boolean isEmailChanged=(user.getEmail()!=null&&!user.getEmail().equals(email) ||
                email!=null&&!email.equals(user.getEmail()));

        if(isEmailChanged) {
            user.setEmail(email);
            if(!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if(!StringUtils.isEmpty(password))
            user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        if(isEmailChanged)
            sendMessage(user);
    }
}
