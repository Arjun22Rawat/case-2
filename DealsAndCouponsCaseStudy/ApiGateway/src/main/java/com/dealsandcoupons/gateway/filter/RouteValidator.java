package com.dealsandcoupons.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

//import org.springframework.cglib.core.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of( //does not require special security and authentication checks.
    		"/loginService/generatetoken",
    		"/loginService/loginForm",
    		
            "/itemService/getProducts",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}// checks whether the predicate in secure or not by checking opneApiEndPoints
