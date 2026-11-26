package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.dao.PokemonDao;
import com.example.model.Pokemon;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Define o endereço: /pokedex
@WebServlet("/pokedex")
public class PokedexServlet extends HttpServlet {

    // doGet é chamado quando o navegador ABRE a página (requisição GET)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PokemonDao dao = new PokemonDao();

        try {
            // 1. busca a lista no banco
            List<Pokemon> lista = dao.listar();

            // 2. guarda a lista com o nome "pokemons" (o JSP vai usar esse nome)
            req.setAttribute("pokemons", lista);

            // 3. encaminha para o JSP desenhar a página
            req.getRequestDispatcher("/WEB-INF/views/publico/lista.jsp")
               .forward(req, resp);

        } catch (SQLException e) {
            // se o banco der erro, repassa para o Tomcat mostrar
            throw new ServletException(e);
        }
    }
}