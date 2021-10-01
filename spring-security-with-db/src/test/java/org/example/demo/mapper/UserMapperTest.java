package org.example.demo.mapper;

import org.example.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUserByUsername() {
        User user = userMapper.selectByUsername("user");
        Assertions.assertEquals("user", user.getUsername());
        Assertions.assertEquals("user@outlook.com", user.getEmail());
        Assertions.assertEquals("USER", user.getRoles());

        user = userMapper.selectByUsername("admin");
        Assertions.assertEquals("admin", user.getUsername());
        Assertions.assertEquals("admin@outlook.com", user.getEmail());
        Assertions.assertEquals("USER,ADMIN", user.getRoles());
    }
}
