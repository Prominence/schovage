package dev.zinchenko.schovage.app.auth.filter;

import dev.zinchenko.schovage.app.apikey.service.ApiKeyService;
import dev.zinchenko.schovage.app.user.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityApiKeyFilter extends OncePerRequestFilter {
    private final ApiKeyService apiKeyService;

    public SecurityApiKeyFilter(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader("Schovage-API-Key");
        if (apiKey != null && !apiKey.isEmpty()) {
            apiKeyService.findEntityByKey(apiKey).ifPresent(apiKeyEntity -> {
                final User owner = apiKeyEntity.getOwner();
                Authentication authentication = new UsernamePasswordAuthenticationToken(owner, null, owner.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        }
        filterChain.doFilter(request, response);
    }
}
