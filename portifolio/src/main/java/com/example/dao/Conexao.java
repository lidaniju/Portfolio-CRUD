package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:54321/portifolio";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgre";

    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do PostgreSQL não encontrado", e);
        }
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

}
