package com.job.rapid.company;

import com.allanweber.java.core.mongo.audit.user.UserAuditableHelper;
import com.allanweber.java.core.mongo.tenancy.TenancyHelper;
import com.allanweber.java.core.security.model.ContextUser;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
@Component
public class AuthenticationHelper implements UserAuditableHelper, TenancyHelper {

    @Override
    public Mono<String> getUserName() {
        return getAuthenticatedUser()
                .map(ContextUser::getUsername);
    }

    @Override
    public Mono<String> getTenancy() {
        return getAuthenticatedUser()
                .map(ContextUser::getTenancy)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("Authenticated user does not have a tenancy.")));
    }


    private Mono<ContextUser> getAuthenticatedUser() {
        return ReactiveSecurityContextHolder.getContext()
                .filter(Objects::nonNull)
                .map(SecurityContext::getAuthentication)
                .filter(it -> Optional.ofNullable(it).map(Authentication::isAuthenticated).orElse(false))
                .map(Authentication::getPrincipal)
                .cast(ContextUser.class);
    }
}
