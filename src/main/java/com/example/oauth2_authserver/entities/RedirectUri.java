package com.example.oauth2_authserver.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
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
