package com.example.oauth2_authserver.entities;

import com.example.oauth2_authserver.converters.GrantTypeToAuthGrantTypeConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "clients")
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;
    private String secret;
    private String authMethod;

    @ManyToMany(targetEntity = RedirectUri.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<RedirectUri> redirectUris = new HashSet<>();

    @ManyToMany(targetEntity = Scope.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Scope> scopes = new HashSet<>();

    @ManyToMany(targetEntity = GrantType.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<GrantType> grantTypes = new HashSet<>();

    public Client(String clientId, String secret, String redirectUri, String authMethod) {
        this.clientId = clientId;
        this.secret = secret;
        this.authMethod = authMethod;
    }

    public static Client from(RegisteredClient registeredClient) {
        Client client = new Client();
        client.setClientId(registeredClient.getClientId());
        client.setSecret(registeredClient.getClientSecret());
        client.setRedirectUris(registeredClient.getRedirectUris().stream().map(RedirectUri::new).collect(Collectors.toSet()));
        client.setScopes(registeredClient.getScopes().stream().map(Scope::new).collect(Collectors.toSet()));
        client.setGrantTypes(registeredClient.getAuthorizationGrantTypes().stream().map(GrantType::new).collect(Collectors.toSet()));
        return client;
    }

    public static RegisteredClient from(Client client) {
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
