package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/portifolio";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgre";

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // TESTE: apague este main depois de ver "Conectou!"
    public static void main(String[] args) {
        try (Connection con = getConexao()) {
            System.out.println("Conectou!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
