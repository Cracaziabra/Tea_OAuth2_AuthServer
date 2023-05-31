package com.example.oauth2_authserver.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "scopes")
@NoArgsConstructor
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Convert(converter = ScopeToStringConverter.class)
    private String scope;

    public Scope(String scope) {
        this.scope = scope;
    }
}
