package com.parsakav.langclass;

import com.parsakav.langclass.model.User;
import com.parsakav.langclass.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class LangclassApplicationTests {
@Autowired
    UserRepository userRepository;
    @Test
    void contextLoads() {
      User user=userRepository.selectById(1L);
       System.out.println(user.getUsername());
    //   Assert.
    }

}
