package pl.backyard.backyardleaguebackend.security.filter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.crud.UserQueryService;
import pl.backyard.backyardleaguebackend.security.cache.CurrentSessionCache;
import pl.backyard.backyardleaguebackend.security.helper.JwtHelper;
import pl.backyard.backyardleaguebackend.security.model.AuthenticatedUser;
import pl.backyard.backyardleaguebackend.security.service.JwtManageService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static pl.backyard.backyardleaguebackend.security.helper.JwtHelper.TOKEN_HEADER;


public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtManageService jwtManageService;
    private final UserQueryService userQueryService;
    private final CurrentSessionCache currentSessionCache;
    private final List<String> authList;

    public JwtAuthenticationFilter(JwtManageService jwtManageService, UserQueryService userQueryService, CurrentSessionCache currentSessionCache, String[] authList) {
        this.jwtManageService = jwtManageService;
        this.userQueryService = userQueryService;
        this.currentSessionCache = currentSessionCache;
        this.authList = List.of(authList);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var jwtToken = request.getHeader(TOKEN_HEADER);
        try {
            verifyToken(jwtToken, request, response);
        } catch (JwtException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request, response);
    }

    private void verifyToken(String jwtToken, HttpServletRequest request, HttpServletResponse response) {
        if (shouldSkip(request.getServletPath())) {
            return;
        }

        if (Objects.isNull(jwtToken) || !jwtManageService.verify(jwtToken)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        var claims = jwtManageService.getClaims(jwtToken);
        var subject = JwtHelper.getSubject(claims);
        var session = currentSessionCache.checkAndGetSession(subject, JwtHelper.getClientId(request));
        var user = userQueryService.getOptByUsernameOrEmail(session.username())
                .orElseThrow(() -> new JwtException("Cannot find user with username: " + session.username()));
        var authenticatedUser = new AuthenticatedUser(user);
        var authentication = new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean shouldSkip(String path) {
        return authList.stream()
                .anyMatch(authSkipPath -> authSkipPath.contains("**")
                        ? path.startsWith(authSkipPath.replace("**", ""))
                        : Objects.equals(path, authSkipPath));
    }

}
