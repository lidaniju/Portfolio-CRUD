package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.example.dao.PokemonDao;
import com.example.model.Pokemon;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/pokemons")
public class AdminServlet extends HttpServlet {

    private final PokemonDao dao = new PokemonDao();

    // GET: mostra a lista, o formulário novo ou o formulário de edição
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String acao = req.getParameter("acao");

        try {
            if ("novo".equals(acao)) {
                req.getRequestDispatcher("/WEB-INF/views/admin/form.jsp").forward(req, resp);

            } else if ("editar".equals(acao)) {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("pokemon", dao.buscarPorId(id));
                req.getRequestDispatcher("/WEB-INF/views/admin/form.jsp").forward(req, resp);

            } else {
                req.setAttribute("pokemons", dao.listar());
                req.getRequestDispatcher("/WEB-INF/views/admin/lista.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // POST: salva (novo ou edição) ou exclui
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String acao = req.getParameter("acao");

        try {
            if ("excluir".equals(acao)) {
                dao.excluir(Integer.parseInt(req.getParameter("id")));

            } else {
                Pokemon p = new Pokemon();
                p.setNumero(Integer.parseInt(req.getParameter("numero")));
                p.setNome(req.getParameter("nome"));
                p.setTipo1(req.getParameter("tipo1"));

                String tipo2 = req.getParameter("tipo2");
                p.setTipo2(tipo2 == null || tipo2.isBlank() ? null : tipo2);

                p.setDescricao(req.getParameter("descricao"));
                p.setImagem(req.getParameter("imagem"));

                String idTexto = req.getParameter("id");
                if (idTexto == null || idTexto.isEmpty()) {
                    dao.inserir(p);
                } else {
                    p.setId(Integer.parseInt(idTexto));
                    dao.atualizar(p);
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/pokemons");
    }
}