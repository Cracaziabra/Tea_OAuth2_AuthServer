package com.example.oauth2_authserver.services;

import com.example.oauth2_authserver.entities.User;
import com.example.oauth2_authserver.model.SecurityUser;
import com.example.oauth2_authserver.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomUserDetailsServiceTest {
    static UserRepository userRepository = mock(UserRepository.class);

    @InjectMocks
    CustomUserDetailsService service;

    @BeforeAll
    static void setup() {
        User user = new User();
        user.setUsername("admin");
        when(userRepository.findUserByUsername("admin")).thenReturn(Optional.of(user));
    }

    @Test
    void findExistedUserByUsername() {
        SecurityUser foundedUser = (SecurityUser) service.loadUserByUsername("admin");
        assertNotNull(foundedUser);
        assertEquals("admin", foundedUser.getUsername());
    }

    @Test
    void catchUsernameNotFoundException() {
        Exception e = assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("neadmin"));
        assertEquals("Can't find user with username: neadmin", e.getMessage());
    }
}