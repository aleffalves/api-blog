package com.github.aleffalves.apiblog.security;

import com.github.aleffalves.apiblog.utils.ConstantsUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Objects;

public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver resolver;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, HandlerExceptionResolver resolver) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.resolver = resolver;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        if(token != null){
            try {
                boolean tokenValido = jwtTokenProvider.validadeToken(token);
                if(tokenValido){
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    if(authentication != null){
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }

            }catch (Exception e){
                HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                resolver.resolveException(httpServletRequest, Objects.requireNonNull(httpServletResponse), null, e);
            }
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
