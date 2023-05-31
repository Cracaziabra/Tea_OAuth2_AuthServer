package com.example.oauth2_authserver.entities;

import com.example.oauth2_authserver.converters.GrantTypeToAuthGrantTypeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Data
@Entity
@Table(name = "grantTypes")
@NoArgsConstructor
public class GrantType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = GrantTypeToAuthGrantTypeConverter.class)
    private AuthorizationGrantType grantType;

    public GrantType(AuthorizationGrantType grantType) {
        this.grantType = grantType;
    }
}
