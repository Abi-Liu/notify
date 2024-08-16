package com.abiliu.notify.config;

import com.abiliu.notify.entities.User;
import com.abiliu.notify.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@Data
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private final static List<String> excludedURLs = List.of("/users", "users/login");

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        if (excludedURLs.stream().noneMatch(x -> x.equals(path))) {
            String apiKey = request.getHeader("Authorization");
            Optional<User> user = userRepository.findByApiKey(apiKey);
            if (user.isEmpty()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized: missing api key");
                return;
            }

            request.setAttribute("userId", user.get().getId());
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}

