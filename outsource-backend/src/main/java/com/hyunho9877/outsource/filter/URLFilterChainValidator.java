package com.hyunho9877.outsource.filter;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class URLFilterChainValidator implements FilterChainValidator{

    private final Set<String> noAuthorizationNeededURL;

    public URLFilterChainValidator() {
        this.noAuthorizationNeededURL = new HashSet<>();
        noAuthorizationNeededURL.add("/ws");
        noAuthorizationNeededURL.add("/api/v1/auth/registration");
        noAuthorizationNeededURL.add("/api/v1/auth/do");
        noAuthorizationNeededURL.add("/api/v1/auth/token/refresh");
    }

    @Override
    public boolean validate(String url) {
        return noAuthorizationNeededURL.contains(url);
    }
}
