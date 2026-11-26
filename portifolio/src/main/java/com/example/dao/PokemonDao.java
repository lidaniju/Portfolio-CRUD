package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Pokemon;

public class PokemonDao {
public List<Pokemon> listar() throws SQLException {
        List<Pokemon> lista = new ArrayList<>();
        String sql = "SELECT * FROM pokemon ORDER BY numero";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pokemon p = new Pokemon();
                p.setId(rs.getInt("id"));
                p.setNumero(rs.getInt("numero"));
                p.setNome(rs.getString("nome"));
                p.setTipo1(rs.getString("tipo1"));
                p.setTipo2(rs.getString("tipo2"));
                p.setDescricao(rs.getString("descricao"));
                p.setImagem(rs.getString("imagem"));
                lista.add(p);
            }
        }
        return lista;
    }
        public Pokemon buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pokemon WHERE id = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pokemon p = new Pokemon();
                    p.setId(rs.getInt("id"));
                    p.setNumero(rs.getInt("numero"));
                    p.setNome(rs.getString("nome"));
                    p.setTipo1(rs.getString("tipo1"));
                    p.setTipo2(rs.getString("tipo2"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setImagem(rs.getString("imagem"));
                    return p;
                }
            }
        }
        return null;
    }

    public void inserir(Pokemon p) throws SQLException {
        String sql = "INSERT INTO pokemon (numero, nome, tipo1, tipo2, descricao, imagem) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getNumero());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getTipo1());
            ps.setString(4, p.getTipo2());
            ps.setString(5, p.getDescricao());
            ps.setString(6, p.getImagem());
            ps.executeUpdate();
        }
    }

    public void atualizar(Pokemon p) throws SQLException {
        String sql = "UPDATE pokemon SET numero=?, nome=?, tipo1=?, tipo2=?, "
                   + "descricao=?, imagem=? WHERE id=?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getNumero());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getTipo1());
            ps.setString(4, p.getTipo2());
            ps.setString(5, p.getDescricao());
            ps.setString(6, p.getImagem());
            ps.setInt(7, p.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM pokemon WHERE id = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
