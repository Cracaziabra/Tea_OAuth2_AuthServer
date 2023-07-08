package com.example.oauth2_authserver.entities;

import com.example.oauth2_authserver.converters.GrantTypeToAuthGrantTypeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
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
    @ToString.Exclude
    private Set<RedirectUri> redirectUris = new HashSet<>();

    @ManyToMany(targetEntity = Scope.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @ToString.Exclude

    private Set<Scope> scopes = new HashSet<>();

    @ManyToMany(targetEntity = GrantType.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @ToString.Exclude
    private Set<GrantType> grantTypes = new HashSet<>();

    public Client(String clientId, String secret, String redirectUri, String authMethod) {
        this.clientId = clientId;
        this.secret = secret;
        this.authMethod = authMethod;
    }
}
