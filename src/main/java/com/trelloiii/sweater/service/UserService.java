package com.trelloiii.sweater.service;

import com.trelloiii.sweater.domain.Role;
import com.trelloiii.sweater.domain.User;
import com.trelloiii.sweater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyMailSender myMailSender;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    public boolean addUser(User user){
        User fromDb=userRepository.findByUsername(user.getUsername());
        if(fromDb!=null){
            return true;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        if(!StringUtils.isEmpty(user.getUsername())){
            String message="Hello activation link: http://localhost:8080/activate/"+user.getActivationCode();
            myMailSender.send(user.getEmail(),"Activation code",message);
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
}
