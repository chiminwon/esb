package com.ming.password;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Oauth2AppTest {

  @Test
  public void contextLoads() {
    System.out.println(new BCryptPasswordEncoder().encode("123456"));
  }
}
