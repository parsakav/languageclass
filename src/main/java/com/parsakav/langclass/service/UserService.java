package com.parsakav.langclass.service;

import com.parsakav.langclass.model.User;

public interface UserService {

     User findUserByUsername(String userName);

     void saveUser(String username, String fullname, Long phonenumber, String password);
     void saveUser(User user);
}
