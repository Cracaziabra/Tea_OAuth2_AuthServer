package com.example.oauth2_authserver.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "redirectUri")
@NoArgsConstructor
public class RedirectUri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String redirectUri;

    public RedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
