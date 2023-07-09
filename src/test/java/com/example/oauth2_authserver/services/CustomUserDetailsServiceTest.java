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
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class CustomUserDetailsServiceTest {
    @InjectMocks
    CustomUserDetailsService service;

    @Mock
    UserRepository userRepository;

    @Test
    void findExistedUserByUsername() {
        User user = new User("admin", "password");
        when(userRepository.findUserByUsername("admin")).thenReturn(Optional.of(user));
        var foundedUser = service.loadUserByUsername("admin");
        assertNotNull(foundedUser);
        assertEquals("admin", foundedUser.getUsername());
        assertEquals("password", foundedUser.getPassword());
    }

    @Test
    void catchUsernameNotFoundException() {
        Exception e = assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("neadmin"));
        assertEquals("Can't find user with username: neadmin", e.getMessage());
    }
}