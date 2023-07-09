package com.example.oauth2_authserver.services;

import com.example.oauth2_authserver.entities.Client;
import com.example.oauth2_authserver.entities.GrantType;
import com.example.oauth2_authserver.entities.RedirectUri;
import com.example.oauth2_authserver.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class CustomClientServiceTest {

    @InjectMocks
    private CustomClientService service;

    @Mock
    private ClientRepository clientRepository;

    @Test
    void testCorrectSave() {
        RegisteredClient regClient = mock(RegisteredClient.class);
        service.save(regClient);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testRegClientFromClient() {
        Client client = new Client();
        client.setClientId("testClient");
        client.setAuthMethod("client-secret-basic");
        client.setGrantTypes(Set.of(new GrantType(AuthorizationGrantType.AUTHORIZATION_CODE)));
        client.setRedirectUris(Set.of(new RedirectUri("abcdef")));
        assertDoesNotThrow(() -> service.regClientFromClient(client));
        RegisteredClient registeredClient = service.regClientFromClient(client);
        assertNotNull(registeredClient);
        assertEquals(client.getClientId(), registeredClient.getClientId());
    }
}