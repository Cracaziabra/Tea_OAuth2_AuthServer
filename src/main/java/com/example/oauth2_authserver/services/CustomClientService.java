package com.example.oauth2_authserver.services;

import com.example.oauth2_authserver.entities.Client;
import com.example.oauth2_authserver.entities.GrantType;
import com.example.oauth2_authserver.entities.RedirectUri;
import com.example.oauth2_authserver.entities.Scope;
import com.example.oauth2_authserver.repositories.ClientRepository;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    public CustomClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        Client client = new Client();
        client.setClientId(registeredClient.getClientId());
        client.setSecret(registeredClient.getClientSecret());
        client.setRedirectUris(registeredClient.getRedirectUris().stream().map(RedirectUri::new).collect(Collectors.toSet()));
        client.setScopes(registeredClient.getScopes().stream().map(Scope::new).collect(Collectors.toSet()));
        client.setGrantTypes(registeredClient.getAuthorizationGrantTypes().stream().map(GrantType::new).collect(Collectors.toSet()));
        clientRepository.save(client);
    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepository.findById(Long.valueOf(id)).orElseThrow();
        return regClientFromClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepository.findClientByClientId(clientId).orElseThrow();
        return regClientFromClient(client);
    }

    public RegisteredClient regClientFromClient(Client client) {
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getAuthMethod()))
                .scopes(a -> a.addAll(client.getScopes().stream().map(Scope::getScope).collect(Collectors.toSet())))
                .redirectUris(uris -> uris.addAll(client.getRedirectUris().stream().map(RedirectUri::getRedirectUri).collect(Collectors.toSet())))
                .authorizationGrantTypes(a -> a.addAll(client.getGrantTypes().stream().map(GrantType::getGrantType).collect(Collectors.toSet())))
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofMinutes(1)).build())
                .build();
    }
}
