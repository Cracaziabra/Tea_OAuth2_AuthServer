package com.example.oauth2_authserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

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

    public Client(String clientId, String secret, String authMethod) {
        this.clientId = clientId;
        this.secret = secret;
        this.authMethod = authMethod;
    }
}
