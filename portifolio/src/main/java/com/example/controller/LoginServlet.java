package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.example.dao.UsuarioDao;
import com.example.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // Abrir a página de login
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    // Enviar o formulário de login
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");

        UsuarioDao dao = new UsuarioDao();

        try {
            Usuario u = dao.autenticar(login, senha);

            if (u != null) {
                // login certo: guarda na sessão e vai para a área do admin
                req.getSession().setAttribute("usuarioLogado", u);
                resp.sendRedirect(req.getContextPath() + "/admin/pokemons");
            } else {
                // login errado: volta para o formulário com mensagem
                req.setAttribute("erro", "Login ou senha incorretos.");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}