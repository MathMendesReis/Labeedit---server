package com.mendes.Labeedit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mendes.Labeedit.providers.JWTAppUserProvider;
import com.mendes.Labeedit.utils.ApiRoutes;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class SecurityAppUserFilter extends OncePerRequestFilter {
  @Autowired
  private JWTAppUserProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // SecurityContextHolder.getContext().setAuthentication(null);
    String header = request.getHeader("Authorization");
    if (request.getRequestURI().startsWith(ApiRoutes.POSTS_BASE) | request.getRequestURI().startsWith(ApiRoutes.REACT_BASE)) {
      if(header != null){
        var token = jwtProvider.validateToken(header);
        if (token == null) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }
        var roles = token.getClaim("roles").asList(Object.class);

        var grants = roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
            .toList();
        request.setAttribute("app_user_id", token.getSubject());
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null,
            grants);

        SecurityContextHolder.getContext().setAuthentication(auth);
      }

    }

    filterChain.doFilter(request, response);
  }

}
