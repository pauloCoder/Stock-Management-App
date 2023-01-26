package fr.gestiondestock.config;

import fr.gestiondestock.services.auth.ApplicationUserDetailsService;
import fr.gestiondestock.utils.JwtUtil;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static fr.gestiondestock.utils.Constants.AUTHORIZATION;
import static fr.gestiondestock.utils.Constants.BEARER;

@Component
public class ApplicationRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final ApplicationUserDetailsService userDetailsService;

    @Autowired
    public ApplicationRequestFilter(JwtUtil jwtUtil, ApplicationUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHORIZATION);
        final SecurityContext securityContext = SecurityContextHolder.getContext();

        String userEmail = null;
        String jwt = null;
        String idEntreprise = null;

        if (StringUtils.hasLength(authHeader) && authHeader.startsWith(BEARER)) {
            jwt = extractJwt(authHeader);
            userEmail = jwtUtil.extractUsername(jwt);
            idEntreprise = jwtUtil.extractIdEntreprise(jwt);
        }

        if (StringUtils.hasLength(userEmail) && securityContext.getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (Boolean.TRUE.equals(jwtUtil.validateToken(jwt , userDetails))) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        MDC.put("idEntreprise", idEntreprise);
        filterChain.doFilter(request, response);

    }

    private String extractJwt(String authHeader) {
        return authHeader.substring(7);
    }
}
