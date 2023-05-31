package com.example.oauth2_authserver;

import com.example.oauth2_authserver.converters.GrantTypeToAuthGrantTypeConverter;
import com.example.oauth2_authserver.entities.*;
import com.example.oauth2_authserver.repositories.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.OidcScopes;

import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class OAuth2AuthServerApplication {

    // http://localhost:9000/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=http://localhost:8080/&code_challenge=q5r6cC2m1SHZr6PAX1cnPZCUInkiNcKBMV7iWUPzmIg&code_challenge_method=S256

    public static void main(String[] args) {
        SpringApplication.run(OAuth2AuthServerApplication.class, args);
    }

//    @Bean
//    public ApplicationRunner dataLoader(UserRepository userRepo,
//                                        ClientRepository clientRepo,
//                                        GrantTypeRepository grantTypeRepo,
//                                        ScopeRepository scopeRepo,
//                                        RoleRepository roleRepo,
//                                        PasswordEncoder passwordEncoder) {
//        return args -> {
//            User user = new User("user", passwordEncoder.encode("123"));
//            Client client = new Client("client", passwordEncoder.encode("secret"), "http://localhost:8080/", "client_secret_basic");
//            Client client2 = new Client("client2", passwordEncoder.encode("secret"), "http://localhost:8080/", "client_secret_basic");
//            GrantType grantType1 = new GrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
//            GrantType grantType2 = new GrantType(AuthorizationGrantType.REFRESH_TOKEN);
//            grantTypeRepo.save(grantType1);
//            grantTypeRepo.save(grantType2);
//            client.getGrantTypes().addAll(List.of(grantType1, grantType2));
//            client2.getGrantTypes().addAll(List.of(grantType1, grantType2));
//            Scope openid = new Scope(OidcScopes.OPENID);
//            Scope profile = new Scope(OidcScopes.PROFILE);
//            scopeRepo.save(openid);
//            scopeRepo.save(profile);
//            client.getScopes().addAll(List.of(openid, profile));
//            client2.getScopes().addAll(List.of(openid, profile));
//            Role readRole = new Role("read");
//            Role writeRole = new Role("write");
//            roleRepo.save(readRole);
//            roleRepo.save(writeRole);
//            user.getRoles().addAll(List.of(readRole, writeRole));
//            userRepo.save(user);
//            clientRepo.save(client);
//            clientRepo.save(client2);
////            clientRepo.delete(client2);
//        };
//    }

}
