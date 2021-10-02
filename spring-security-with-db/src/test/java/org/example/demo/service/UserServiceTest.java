package org.example.demo.service;

import org.example.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testLoadUserByUsername() throws Exception {
        User user = (User) userService.loadUserByUsername("user");
        Assertions.assertEquals("user", user.getUsername());
        Assertions.assertEquals("user@outlook.com", user.getEmail());
        Assertions.assertEquals(1, user.getRoles().size());

        Assertions.assertThrows(UsernameNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                User notExistedUser = (User) userService.loadUserByUsername("username");
            }
        });
    }
}
