package com.example.oauth2_authserver.converters;

import com.example.oauth2_authserver.entities.GrantType;
import jakarta.persistence.AttributeConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Component;

@Component
public class GrantTypeToAuthGrantTypeConverter implements AttributeConverter<AuthorizationGrantType, String> {

    @Override
    public String convertToDatabaseColumn(AuthorizationGrantType authorizationGrantType) {
        return authorizationGrantType.getValue();
    }

    @Override
    public AuthorizationGrantType convertToEntityAttribute(String s) {
        return new AuthorizationGrantType(s);
    }
}
