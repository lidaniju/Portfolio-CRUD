package com.example.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Intercepta tudo que começa com /admin/
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // false = não cria sessão nova se não existir
        HttpSession sessao = req.getSession(false);

        boolean logado = sessao != null && sessao.getAttribute("usuarioLogado") != null;

        if (logado) {
            chain.doFilter(request, response);   // deixa passar
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");   // barra
        }
    }
}