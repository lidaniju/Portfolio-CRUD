package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.Usuario;

public class UsuarioDao {

    // Devolve o Usuario se login e senha baterem, ou null se não existir
    public Usuario autenticar(String login, String senha) throws SQLException {

        // Os ? serão preenchidos com login e senha (evita SQL injection)
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Preenche os ? na ordem: 1 = login, 2 = senha
            ps.setString(1, login);
            ps.setString(2, senha);

            // Só depois de preencher os ? é que executamos
            try (ResultSet rs = ps.executeQuery()) {

                // Se achou uma linha, o login e a senha estão certos
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setLogin(rs.getString("login"));
                    return u;
                }
            }
        }

        // Não achou ninguém
        return null;
    }
}
