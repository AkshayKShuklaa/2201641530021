package com.logger.middleware;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        CustomHttpRequestWrapper wrappedRequest = new CustomHttpRequestWrapper((HttpServletRequest) request);
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        long startTime = System.currentTimeMillis();

        // Log method, URL
        System.out.println("📥 Incoming Request: " + wrappedRequest.getMethod() + " " + wrappedRequest.getRequestURI());

        // Log headers
        System.out.println("Headers:");
        Enumeration<String> headerNames = wrappedRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            System.out.println(header + ": " + wrappedRequest.getHeader(header));
        }

        // Log request body
        System.out.println("Body: " + wrappedRequest.getRequestBody());

        // Continue processing
        chain.doFilter(wrappedRequest, response);

        long duration = System.currentTimeMillis() - startTime;

        // Log status code and duration
        System.out.println("✅ Completed: " + wrappedRequest.getMethod() + " " + wrappedRequest.getRequestURI()
                + " Status: " + httpResponse.getStatus()
                + " in " + duration + " ms");
    }
}
