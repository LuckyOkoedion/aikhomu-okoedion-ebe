package com.AikhomuLuckyOkoedion.OnlineBookStore.gateway;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class ApiGatewayFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        // Log requests for routing simulation
        System.out.println("API Gateway: Request URL - " + request.getRequestURI());

        // Simulate routing by inspecting the request path
        if (request.getRequestURI().contains("/checkout")) {
            // Simulate forwarding to Checkout Service
            request.setAttribute("service", "CheckoutService");
        } else if (request.getRequestURI().contains("/inventory")) {
            // Simulate forwarding to Inventory Service
            request.setAttribute("service", "InventoryService");
        }
        filterChain.doFilter(request, response);
    }
}

