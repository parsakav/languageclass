package com.parsakav.langclass.service;

import com.parsakav.langclass.model.Language;
import com.parsakav.langclass.model.Role;
import com.parsakav.langclass.model.User;
import com.parsakav.langclass.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService{
   private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserByUsername(String userName) {
        return userRepository.findUserByUsername(userName.toLowerCase());
    }

    @Override
    public void saveUser(String username, String fullname, Long phonenumber, String password) {
        User user1=userRepository.findUserByUsername(username);

        if(user1==null) {
            User user = new User();

            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setFullname(fullname);
            user.setPhonenumber(phonenumber);
            user.setUsername(username.toLowerCase());
            Set<Role> ms = new HashSet<>();
            Role r = new Role();
            r.setId(1);
            r.setName("ROLE_USER");

            ms.add(r);
            user.setRoles(ms);

            saveUser(user);
        }
    }

    @Override
    public void saveUser(User user) {

            userRepository.save(user);



    }


}
